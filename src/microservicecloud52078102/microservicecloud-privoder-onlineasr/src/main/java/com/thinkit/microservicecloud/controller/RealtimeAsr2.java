package com.thinkit.microservicecloud.controller;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.thinkit.microservicecloud.entities.onlineasr.RealtimeAsrReq;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.XmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@RestController
public class RealtimeAsr2 {

    private Logger logger = LoggerFactory.getLogger(RealtimeAsr2.class);

  //  @RequestMapping(value="/provider/service/realtimeasr2",method= RequestMethod.POST )
    public ResultInfo realtimeAsr(@RequestBody RealtimeAsrReq info) {


        logger.info("请求参数： "+ info.toString());

        File newFile = new File(info.getNewPath());
        File oldFile = new File(info.getOldPath());


        System.out.println(newFile.length());
        System.out.println(oldFile.length());

        FileInputStream fis = null ;
        int offset = Math.toIntExact(newFile.length() - oldFile.length());

        System.out.println(offset);
        byte[]  buff = new byte[offset];

        try {
            fis =  new FileInputStream(newFile);

            fis.skip(oldFile.length());

            fis.read(buff);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");

       logger.info("realtimeAsr start ...");

        int vad_status = 0;

        Pointer pointer = LibAsr.INSTANCE.tk_asr_create();

        LibAsr.INSTANCE.tk_asr_init(pointer, "./config.ini");

        logger.info("tk_asr_inited ...");
        String[] str ={""};

        logger.info("tk_asr_send_data ...");
        vad_status = LibAsr.INSTANCE.tk_asr_send_data(pointer, buff, buff.length, 1, str);

        if(vad_status>0){
            logger.info("vad_status="+vad_status+"  , have rec result ");
            logger.info("result: " +str[0]);


        }else if(vad_status==0){
            logger.info("vad_status="+vad_status+"  , no rec result ");


        }else{
            //负值表示发生错误
            logger.error("vad_status="+vad_status+"  , errors occur");
            LibAsr.INSTANCE.tk_asr_reset(pointer, 0);
        }


        logger.info("realtimeAsr end ...");

        LibAsr.INSTANCE.tk_asr_delete(pointer);


        logger.info("utf8 :  "+str[0]);


        String result = "";
        if(!str[0].equals("")){
             result = XmlUtil.readXml(str[0]);

           logger.info("xml parse: "+result);
        }

        resultInfo.setResult(result);
        return  resultInfo;
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