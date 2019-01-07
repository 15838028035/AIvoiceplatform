package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.tts.ParamsTTS;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class OnlineTtsController {

    private static final String REST_URL_PREFIX = "http://microservicecloud-onlinetts";

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping(value = "/consumer2/service/tts", method = RequestMethod.POST)
    public void  output(@RequestBody ParamsTTS info, HttpServletResponse response) {

      //  restTemplate.postForObject("http://127.0.0.1:19004/service/output/tts",info);

        byte[] buff = null;
        try {
            //buff = restTemplate.getForObject(url, byte[].class);
          buff =   restTemplate.postForObject("http://127.0.0.1:19004/service/output/tts",info,byte[].class);
        }catch(Exception e){
            System.out.println(e.getMessage());

        }

        response.setContentType("audio/wav");

        try {
            response.getOutputStream().write(buff);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
