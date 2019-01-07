package com.thinkit.microservicecloud.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class RealtimeAsr {

    private Logger logger = LoggerFactory.getLogger(RealtimeAsr.class);

  //  @RequestMapping(value="/provider/service/realtimeasr",method= RequestMethod.POST )
    public ResultInfo realtimeAsr(@RequestBody VoiceRec info) {

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");

       logger.info("realtimeAsr start ...");

       StringBuilder sb = new StringBuilder("");
        byte[] buf = new byte[1600];

        int vad_status = 0;
        //字节流读取文件buffer

        Pointer pointer = LibAsr.INSTANCE.tk_asr_create();


        LibAsr.INSTANCE.tk_asr_init(pointer, "./config.ini");

        logger.info("tk_asr_inited ...");
        String[] str ={""};

        logger.info("tk_asr_send_data ...");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(info.getVoicepath()));


            logger.error("voicepath: "+info.getVoicepath());

            int len = 0;
            while((len = fis.read(buf))!=-1){

                logger.info("len :"+len);

                if(len<1600){
                    byte[] temp = new byte[len];
                    System.arraycopy(buf, 0, temp, 0, len);
                    vad_status = LibAsr.INSTANCE.tk_asr_send_data(pointer, temp, buf.length, 1, str);

                    logger.info("send the last package ... ---------------------------->");
                    if(vad_status>0){
                        logger.info("vad_status="+vad_status+"  , have rec result ");
                        logger.info("result: " +str[0]);
                        sb.append(str[0]);


                    }else if(vad_status==0){
                        logger.info("vad_status="+vad_status+"  , no rec result ");


                    }else{
                        //负值表示发生错误
                        logger.error("vad_status="+vad_status+"  , errors occur");
                        LibAsr.INSTANCE.tk_asr_reset(pointer, 0);
                    }

                    break;
                }


                vad_status = LibAsr.INSTANCE.tk_asr_send_data(pointer, buf, buf.length, 0, str);
                if(vad_status>0){
                   logger.info("vad_status="+vad_status+"  , have rec result ");

                  logger.info("result: " + str[0]);
                  sb.append(str[0]);

                }else if(vad_status==0){
                    logger.info("vad_status="+vad_status+"  , no rec result ");


                }else{
                    //负值表示发生错误
                    logger.error("vad_status="+vad_status+"  , errors occur");
                    LibAsr.INSTANCE.tk_asr_reset(pointer, 0);
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }




        logger.info("realtimeAsr end ...");

        LibAsr.INSTANCE.tk_asr_delete(pointer);

        logger.error("sb: "+sb.toString());

        resultInfo.setResult(sb.toString());
        return  resultInfo;
    }


    public  String  gb2312toUTF8(String source){

        String desc = "";
        try {
            //desc =  new String(source.getBytes("GB2312"),"UTF-8");

            desc =  new String(desc.getBytes("GBK"),"UTF-8");
            //desc = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return desc;
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

        void VAD_MessageCallBack( int message, int wParam, int lParam);
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