package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.TtsInfo;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class VoicePrintController {

    private static final String REST_URL_PREFIX = "http://microservicecloud-voiceprint";

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping(value = "/consumer2/voiceprint/rec", method = RequestMethod.POST)
    public VoiceResult rec(@RequestBody VoiceUser vu)
    {
       return restTemplate.postForObject(REST_URL_PREFIX+"/voiceprint/rec",vu,VoiceResult.class);

    }


}
