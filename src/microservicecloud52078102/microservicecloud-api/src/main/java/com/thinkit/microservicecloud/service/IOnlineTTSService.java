package com.thinkit.microservicecloud.service;

import com.thinkit.microservicecloud.entities.tts.ParamsTTS;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@FeignClient(value = "MICROSERVICECLOUD-ONLINETTS")
public interface IOnlineTTSService {

    @RequestMapping(value = "/service/tts/{text}/{lang}/{speaker}/{speed}/{volume}", method = RequestMethod.GET)
    public byte[]  output(@PathVariable("text") String text, @PathVariable("lang") String lang, @PathVariable("speaker") String speaker, @PathVariable("speed") String speed, @PathVariable("volume") String volume) ;
}
