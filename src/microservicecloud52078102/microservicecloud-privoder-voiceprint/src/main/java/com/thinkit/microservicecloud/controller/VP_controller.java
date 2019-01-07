package com.thinkit.microservicecloud.controller;



import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class VP_controller {



    @Autowired
    private RestTemplate restTemplate;

    @Value("${voiceprint.ServerIp}")
    private  String ServerIp;

    @Value("${voiceprint.ServerPort}")
    private  String ServerPort;

    @RequestMapping(value = "/voiceprint/rec_php", method = RequestMethod.POST)
    public VoiceResult add(@RequestBody VoiceUser vu) {

        VoiceResult vr = new VoiceResult();

        /**
         * type 0 1 2 3  注册 确认 识别 删除
         */



        String url = "http://" + ServerIp + ":" + ServerPort
                + "/ability/spk";



        System.out.println(vu.toString());


        if(vu.getVoicepath()!=null && !"".equals(vu.getVoicepath())){
            if(!new File(vu.getVoicepath()).exists()){
                vr.setStatus("-1");
                vr.setMessage( "error,录音文件不存在.");
                return vr;
            }
        }else{
            vr.setStatus("-1");
            vr.setMessage("error,录音参数名不合法.");
            return vr;
        }
        // 以字节流方法读取文件
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(vu.getVoicepath());
            // 设置一个，每次 装载信息的容器
            byte[] buf = new byte[(int) new File(vu.getVoicepath()).length()];

            // 开始读取数据
            int len = 0;// 每次读取到的数据的长度
            while ((len = fis.read(buf)) != -1) {// len值为-1时，表示没有数据了


                System.out.println("len: " + len);
                System.out.println("url: " + url);
                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, buf, String.class);

                System.out.println(responseEntity.getStatusCode());
                System.out.println(responseEntity.getBody());

                vr.setStatus(""+responseEntity.getStatusCode());
                vr.setMessage(responseEntity.getBody());






            }
        } catch (Exception e) {

        } finally {
            try {
                if(fis!=null)fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return vr;


    }
}
