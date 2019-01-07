package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceUser;
import com.thinkit.microservicecloud.service.IVoicePrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VoicePrint_feignController {

    @Autowired
    private IVoicePrintService service;


    @RequestMapping(value = "/consumer/voiceprint/rec", method = RequestMethod.POST)
    public VoiceResult rec(@RequestBody VoiceUser vu)
    {
      return service.rec(vu);
}


}
