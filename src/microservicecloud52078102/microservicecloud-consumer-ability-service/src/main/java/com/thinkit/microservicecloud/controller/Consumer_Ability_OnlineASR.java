package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.ability.asr.RequestAsrBody;
import com.thinkit.microservicecloud.entities.ability.asr.ResponseAsrBody;
import com.thinkit.microservicecloud.service.IAbilityOnlineAsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Consumer_Ability_OnlineASR {

    @Autowired
   private IAbilityOnlineAsrService iAbilityOnlineAsrService;

    @RequestMapping(value = "/ability/asr",method = RequestMethod.POST)
    public ResponseAsrBody asr(@RequestBody RequestAsrBody info){
       return iAbilityOnlineAsrService.asr(info);
    }

    @RequestMapping(value = "/ability/asr10",method = RequestMethod.POST)
    public ResponseAsrBody asr10(@RequestBody RequestAsrBody info){
        return iAbilityOnlineAsrService.asr10(info);
    }
}
