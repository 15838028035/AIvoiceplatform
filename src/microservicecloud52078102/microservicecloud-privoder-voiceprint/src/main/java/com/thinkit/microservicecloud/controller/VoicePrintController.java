package com.thinkit.microservicecloud.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.thinkit.microservicecloud.entities.voiceprint.*;
import com.thinkit.microservicecloud.entities.voiceprint.auw.RequestAuw;
import com.thinkit.microservicecloud.entities.voiceprint.grs.RequestGrs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

@RestController
public class VoicePrintController {


    private Logger logger = LoggerFactory.getLogger(VoicePrintController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${voiceprint.ServerIp}")
    private  String ServerIp;

    @Value("${voiceprint.ServerPort}")
    private  String ServerPort;

    @RequestMapping(value = "/voiceprint/rec", method = RequestMethod.POST)
    public VoiceResult add(@RequestBody VoiceUser vu) {

        logger.info("/voiceprint/rec  请求参数："+ vu.toString());

        VoiceResult vr = new VoiceResult();

        /**
         * type 0 1 2 3  注册 确认 识别 删除
         */

        String url = "http://" + ServerIp + ":" + ServerPort
                + "/ability/spk";

        String sid =  dealssb(vu);
        int idx = 0;
        if(vu.getType().equals("3")){

           idx =  dealauw3(sid);
        }else{
            idx = dealauw(vu.getVoicepath(),sid);
        }

        String[] strs = dealgrs(sid,idx);

        if(strs[0].equals("5")){
            vr.setStatus("200");
        }else{
            vr.setStatus("-1");
        }

        vr.setMessage(strs[1]);
        return vr;


    }


    public String dealssb(VoiceUser vu){
        RequestEntity entity = new RequestEntity();
        entity.setId(1);
        entity.setJsonrpc("2.0");
        entity.setMethod("deal_request");

        Param params = new Param();
        params.setCmd("ssb");
        params.setSpk_type(vu.getType());
        params.setUid(vu.getUsername());

        entity.setParams(params);

        String url = "http://" + ServerIp + ":" + ServerPort
                + "/ability/spk";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSON.toJSON(entity), String.class);

        logger.info("ssb 请求参数："+ JSON.toJSON(entity));
        logger.info("ssb 返回结果: ");
        logger.info("status code: "+responseEntity.getStatusCode());


        if(responseEntity.getStatusCode()==HttpStatus.OK){
            String result = new String(Base64.getDecoder().decode(responseEntity.getBody()));
            logger.info(result);
            ResponseParams ResponseParams = (ResponseParams)JSON.parseObject(result,new TypeReference<ResponseParams>(){});
            return ResponseParams.getResult().getSid();
        }


         return "";
    }


    public int dealauw(String filepath,String sid){

        RequestAuw requestAuw = new RequestAuw();
        requestAuw.setId(1);
        requestAuw.setJsonrpc("2.0");
        requestAuw.setMethod("deal_request");
        int idx = 1;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filepath));
            // 每次发送的字节大小
            byte[] buf = new byte[1600];

            int len = 0;

            while ((len = fis.read(buf))!=-1){

                com.thinkit.microservicecloud.entities.voiceprint.auw.Param params = new com.thinkit.microservicecloud.entities.voiceprint.auw.Param();
                params.setCmd("auw");
                params.setSid(sid);
                params.setSyncid(idx++);
                if(len<1600){
                    params.setAudioStatus(4);

                    byte[] temp = new byte[len];
                    System.arraycopy(buf,0,temp,0,len);
                    params.setData(Base64.getEncoder().encodeToString(temp));
                }else {
                    params.setAudioStatus(2);

                    params.setData(Base64.getEncoder().encodeToString(buf));
                }

                requestAuw.setParams(params);

                String url = "http://" + ServerIp + ":" + ServerPort
                        + "/ability/spk";

                ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSON.toJSON(requestAuw), String.class);
                logger.info("status code: "+responseEntity.getStatusCode());
                String result = new String(Base64.getDecoder().decode(responseEntity.getBody()));
                logger.info(result);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return  --idx;
    }

    public int dealauw3(String sid){

        RequestAuw requestAuw = new RequestAuw();
        requestAuw.setId(1);
        requestAuw.setJsonrpc("2.0");
        requestAuw.setMethod("deal_request");


        int idx = 1;
        com.thinkit.microservicecloud.entities.voiceprint.auw.Param params = new com.thinkit.microservicecloud.entities.voiceprint.auw.Param();
        params.setCmd("auw");
        params.setSid(sid);
        params.setSyncid(idx);

        params.setAudioStatus(4);
        params.setData("");
        requestAuw.setParams(params);

        String url = "http://" + ServerIp + ":" + ServerPort
                + "/ability/spk";

        System.out.println( JSON.toJSON(requestAuw));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSON.toJSON(requestAuw), String.class);
        logger.info("status code: "+responseEntity.getStatusCode());
        String result = new String(Base64.getDecoder().decode(responseEntity.getBody()));
        logger.info(result);

        return  idx;
    }

    public String[] dealgrs(String sid,int idx){

        RequestGrs grs = new RequestGrs();
        grs.setId(1);
        grs.setJsonrpc("2.0");
        grs.setMethod("deal_request");

        com.thinkit.microservicecloud.entities.voiceprint.grs.Param params = new com.thinkit.microservicecloud.entities.voiceprint.grs.Param();
        params.setCmd("grs");
        params.setSid(sid);
        params.setSyncid(idx);
        grs.setParams(params);


        String url = "http://" + ServerIp + ":" + ServerPort
                + "/ability/spk";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, JSON.toJSON(grs), String.class);
        logger.info("status code: "+responseEntity.getStatusCode());
        String result = new String(Base64.getDecoder().decode(responseEntity.getBody()));
        logger.info(result);

        com.thinkit.microservicecloud.entities.voiceprint.grs.ResponseParams responseParams = (com.thinkit.microservicecloud.entities.voiceprint.grs.ResponseParams) JSON.parseObject(result,new TypeReference<com.thinkit.microservicecloud.entities.voiceprint.grs.ResponseParams>(){});

        String[] strs = {responseParams.getResult().getRecStatus()+"",responseParams.getResult().getResult()};

        return strs;
    }

}
