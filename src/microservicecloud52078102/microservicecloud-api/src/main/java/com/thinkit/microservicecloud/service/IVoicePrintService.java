package com.thinkit.microservicecloud.service;


import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "MICROSERVICECLOUD-VOICEPRINT")
public interface IVoicePrintService {

    @RequestMapping(value = "/voiceprint/rec", method = RequestMethod.POST)
    public VoiceResult rec(@RequestBody VoiceUser vu);
}
