package com.thinkit.microservicecloud.controller;



import com.thinkit.microservicecloud.dao.Results;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import com.thinkit.microservicecloud.service.XmlUtil;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class OnlineASR_controller {

    private Logger logger = LoggerFactory.getLogger(OnlineASR_controller.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${onlineasr.ServerIp}")
    private  String ServerIp;

    @Value("${onlineasr.ServerPort}")
    private  String ServerPort;

    @RequestMapping(value = "/onlineasr/rec", method = RequestMethod.POST)
    public VoiceResult rec( @RequestBody VoiceRec vrec) {


        logger.info("在线识别 录音文件："+ vrec.getVoicepath());

        VoiceResult vr = new VoiceResult();

        if(vrec.getVoicepath()!=null && !"".equals(vrec.getVoicepath())){
            if(!new File(vrec.getVoicepath()).exists()){
                vr.setStatus("-1");
                vr.setMessage( "error,录音文件不存在.");
                logger.error("error,录音文件不存在.");
                return vr;
            }
        }else{
            vr.setStatus("-1");
            vr.setMessage("error,录音参数名不合法.");
            logger.error("error,录音参数名不合法.");
            return vr;
        }

          vr = dealFile(vrec.getVoicepath());


        return vr;

    }


    public  VoiceResult dealFile(String file) {

        VoiceResult vr = new VoiceResult();

        String sessionid = GenerateSessionId.getSid();

        int idx = 1;

        boolean islast = false;
        // 以字节流方法读取文件
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            // 设置一个，每次 装载信息的容器
            byte[] buf = new byte[1024];

            // 开始读取数据
            int len = 0;// 每次读取到的数据的长度
            while ((len = fis.read(buf)) != -1) {// len值为-1时，表示没有数据了

                if(len<1024){
                    islast = true;

        		/*	参数解析：

        			src：byte源数组
        			srcPos：截取源byte数组起始位置（0位置有效）
        			dest,：byte目的数组（截取后存放的数组）
        			destPos：截取后存放的数组起始位置（0位置有效）
        			length：截取的数据长度*/

                    byte[] temp = new byte[len];
                    System.arraycopy(buf, 0, temp, 0, len);

                    String url = generate(sessionid,idx, islast);


                    ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, temp, String.class);

                    System.out.println(responseEntity.getStatusCode());
                    System.out.println(responseEntity.getBody());

                    if(responseEntity.getBody()==null || "".equals(responseEntity.getBody())){
                          vr.setStatus("-1");
                          vr.setMessage("error: asr service return null!");
                          logger.error("error: asr service return null!");
                    }

                    String result = XmlUtil.readXml(responseEntity.getBody());

                    vr.setStatus(responseEntity.getStatusCode()+"");
                    vr.setMessage(result);
                    System.out.println("识别结果："+result);

                    System.out.println("发送次数："+idx);

                    if(islast) {
                        System.out.println("url: "+ url);
                    }

                    return vr;

                }else{

                    if(1024*idx==file.length()) {   //语音长度刚好是每包定义的整数倍情况，需要确认audioStatus=4 最后一块音频
                        islast = true;
                    }else {
                        islast = false;
                    }

                }

                String url = generate(sessionid,idx, islast);

                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, buf, String.class);

                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());

                logger.info("rec result: "+responseEntity.getBody() );

                if(responseEntity.getBody().contains("Could not connect to socket LZ")){
                    logger.error("Error: ASR server is not available");
                    vr.setStatus("-1");
                    vr.setMessage("Error: ASR server is not available");
                    return vr;
                }

                String result = XmlUtil.readXml(responseEntity.getBody());



                vr.setStatus(responseEntity.getStatusCode()+"");
                vr.setMessage(result);
                System.out.println("识别结果："+result);

                System.out.println("发送次数："+idx);

                if(islast) {
                    System.out.println("url: "+ url);
                }

                idx++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return vr;
    }

    public  String generate(String sessionId, int idx, boolean last) {

        String url = "http://"+ServerIp+":"+ServerPort
                + "/asr.php?sid="+ sessionId
                + "&username=" + "zhangbo"
                + "&auth=aaaa"
                + "&idx=" +idx
                + "&islast=" +(last==true ? 1:0)
                + "&did=" +"cccc"
                + "&rectype=1"
                + "&etype=" + "stream"
                + "&dtype=unknow";

        return url;
    }

}
