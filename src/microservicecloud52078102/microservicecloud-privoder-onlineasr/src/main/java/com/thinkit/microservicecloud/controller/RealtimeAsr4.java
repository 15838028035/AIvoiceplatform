package com.thinkit.microservicecloud.controller;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.thinkit.microservicecloud.entities.onlineasr.RealtimeAsrReq;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;

@RestController
public class RealtimeAsr4 {

    private Logger logger = LoggerFactory.getLogger(RealtimeAsr4.class);
    private static Map<String, Pointer> pointerMap = new HashMap<>();

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public  String getkeyvalue(String key){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        System.out.println("redis info: get key:"+key+" --> value:"+value);
        return value;
    }

    public  void setkeyvalue(String key,String value){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    @RequestMapping(value="/provider/service/realtimeasr",method= RequestMethod.POST )
    public ResultInfo realtimeAsr(@RequestBody RealtimeAsrReq info) {

        logger.info("请求参数： "+ info.toString());

        File newFile = new File(info.getNewPath());
        File oldFile = new File(info.getOldPath());

        logger.debug("newFile.length():"+newFile.length());
        logger.debug("oldFile.length():"+oldFile.length());

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");

        StringBuilder stringBuilder = new StringBuilder("");
        logger.info("realtimeAsr start ...");

        int vad_status = 0;
        Pointer pointer;
        logger.info("pointerMap中有"+pointerMap.size()+"个pointer");

        if (pointerMap.containsKey(info.getId())){
            pointer = pointerMap.get(info.getId());
        }else {
            pointer = LibAsr.INSTANCE.tk_asr_create();
            LibAsr.INSTANCE.tk_asr_init(pointer, "./config.ini");
            pointerMap.put(info.getId(),pointer);
        }
        logger.info("本次执行后pointerMap中有"+pointerMap.size()+"个pointer");

        logger.info("tk_asr_inited ...");
        String[] str ={""};

        logger.info("tk_asr_send_data ...");

        FileInputStream fis = null ;

        String value = getkeyvalue(info.getId());

        int langSize = 0;
        if(value!=null && !value.equals("")){
            langSize = Integer.parseInt(value);
        }

        byte[] buff = new byte[1600];

        try {
            fis =  new FileInputStream(newFile);

            fis.skip(oldFile.length()-langSize);

            int len;// 每次读取到的数据的长度
            while ((len = fis.read(buff)) != -1) {   // len值为-1时，表示没有数据了
                if (len < 1600) {
                    if(info.getType().equals("true")){
                        	/*	参数解析：
        			src：byte源数组
        			srcPos：截取源byte数组起始位置（0位置有效）
        			dest,：byte目的数组（截取后存放的数组）
        			destPos：截取后存放的数组起始位置（0位置有效）
        			length：截取的数据长度*/

                        byte[] temp = new byte[len];
                        System.arraycopy(buff, 0, temp, 0, len);

                        vad_status = LibAsr.INSTANCE.tk_asr_send_data(pointer, temp, temp.length, 1, str);

                        if(vad_status>0){
                            logger.info("vad_status="+vad_status+"  , have rec result ");
                            logger.info("result: " +str[0]);
                            stringBuilder.append(getRecfromXml(str[0]));

                        }else if(vad_status==0){
                            logger.info("vad_status="+vad_status+"  , no rec result ");
                        }else{
                            //负值表示发生错误
                            logger.error("vad_status="+vad_status+"  , errors occur");
                            LibAsr.INSTANCE.tk_asr_reset(pointer, 0);
                        }
                    }else{
                        if(len==0)
                            setkeyvalue(info.getId(),"");
                        else
                            setkeyvalue(info.getId(),len+"");
                    }

                }else {
                    vad_status = LibAsr.INSTANCE.tk_asr_send_data(pointer, buff, buff.length, 0, str);
                    if (vad_status > 0) {
                        logger.info("vad_status=" + vad_status + "  , have rec result ");
                        logger.info("result: " + str[0]);
                        stringBuilder.append(getRecfromXml(str[0]));
                    } else if (vad_status == 0) {
                        logger.info("vad_status=" + vad_status + "  , no rec result ");
                    } else {
                        //负值表示发生错误
                        logger.error("vad_status=" + vad_status + "  , errors occur");
                        LibAsr.INSTANCE.tk_asr_reset(pointer, 0);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logger.info("realtimeAsr end ...");

        if (info.getType().equals("true")){
            LibAsr.INSTANCE.tk_asr_delete(pointer);
            pointerMap.remove(info.getId());
            File newAudio = new File(info.getNewPath());
            if (newAudio.exists()){
               // newAudio.delete();
            }
        }
        logger.info("本次运行结束后pointerMap中有"+pointerMap.size()+"个pointer");

        logger.info("utf8 :  "+stringBuilder.toString());

        resultInfo.setResult(stringBuilder.toString());

        File file = new File(info.getOldPath());
        if (file.exists()){
            //file.delete();
        }
        return  resultInfo;
    }


    @RequestMapping(value="/provider/service/realtimeAsrIndex",method= RequestMethod.POST )
    public ResultInfo realtimeAsrIndex(@RequestBody RealtimeAsrReq info) {

        // int idx = 1;

        logger.info("请求参数： "+ info.toString());

        File newFile = new File(info.getNewPath());
        int len = (int)newFile.length() / 1600;
        for (int i = 0; i <len ;){


        }
        //File oldFile = new File(info.getOldPath());


        return  null;
    }

/*    public  byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }*/

    public  String  getRecfromXml(String xml){
        String result = "";
        if(!xml.equals("")){
            result = XmlUtil.readXml(xml);

            logger.info("xml parse: "+result);
        }

        return  result;
    }






    public interface LibAsr extends Library{

        LibVad FOO = LibVad.INSTANCE;
        LibCurl Curl = LibCurl.INSTANCE;
        LibAsr INSTANCE = (LibAsr) Native.loadLibrary("tkasr",LibAsr.class);

        Pointer tk_asr_create();
        void tk_asr_delete(Pointer hAsr);
        int tk_asr_init(Pointer hAsr, String cfgfile);
        int tk_asr_exit(Pointer hAsr);
        void tk_asr_reset(Pointer hAsr, int vadOption);
        int tk_asr_send_data(Pointer hAsr, byte[] buffer, int length, int isLast, String[] asrStrResult);
        int tk_asr_recognize(Pointer hAsr, String buffer, int length, int isLast, String[] asrStrResult, int vadStatus);
    }


    public interface LibVad extends Library{
        LibCurl Curl = LibCurl.INSTANCE;

        LibVad INSTANCE = (LibVad) Native.loadLibrary("tkvad",LibVad.class);

        void VAD_MessageCallBack(int message, int wParam, int lParam);
        void VAD_DataCallBackOld(String buf, int len);
        void VAD_DataCallBack(String buf, int len, int isLast);
    }

    public interface LibCurl extends Library{
        LibCurl INSTANCE = (LibCurl) Native.loadLibrary("curl",LibCurl.class);

    }

    public static class vad_data extends Structure{
        public static class ByReference extends vad_data implements Structure.ByReference{
            public ByReference(){}
        }
        public static class ByValue extends vad_data implements Structure.ByValue{
            public ByValue(){}
        }
        public byte isLast;
        public String voiceBuf;
        public int voiceLen;


        protected List getFieldOrder() {
            return Arrays.asList(new String[]{
                    "isLast","voiceBuf","voiceLen"
            });
        }
    }


    public static class vad_status extends Structure{
        public static class ByReference extends vad_status implements Structure.ByReference{
            public ByReference(){}
        }
        public static class ByValue extends vad_status implements Structure.ByValue{
            public ByValue(){}
        }
        public byte status;
        public byte setVoiceEnd;
        public int totalTime;
        public int startTime;
        public int stopTime;
        public vad_data data;

        protected List getFieldOrder() {
            return null;
        }
    }



}