package com.thinkit.microservicecloud.service;


import com.thinkit.microservicecloud.entities.ability.asr.RequestAsrBody;
import com.thinkit.microservicecloud.entities.ability.asr.ResponseAsrBody;
import com.thinkit.microservicecloud.entities.onlineasr.PointCut;
import com.thinkit.microservicecloud.entities.onlineasr.RealtimeAsrReq;
import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "MICROSERVICECLOUD-ABILITY-ONLINEASR")
public interface IAbilityOnlineAsrService {

    @RequestMapping(value = "/provider/ability/asr",method = RequestMethod.POST)
    public ResponseAsrBody asr(@RequestBody RequestAsrBody info);

    @RequestMapping(value = "/provider/ability/asr10",method = RequestMethod.POST)
    public ResponseAsrBody asr10(@RequestBody RequestAsrBody info);
}
