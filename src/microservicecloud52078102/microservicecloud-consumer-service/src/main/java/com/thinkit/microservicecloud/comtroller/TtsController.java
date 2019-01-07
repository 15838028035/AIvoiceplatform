package com.thinkit.microservicecloud.comtroller;

import com.thinkit.microservicecloud.entities.TtsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class TtsController {

    private static final String REST_URL_PREFIX = "http://microservicecloud-tts";

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping(value = "/consumer/tts/list", method = RequestMethod.GET)
    public List<TtsInfo> list()
    {
       return restTemplate.getForObject(REST_URL_PREFIX+"/tts/list",List.class);

    }


    @RequestMapping(value = "/consumer/tts/get/{id}", method = RequestMethod.GET)
    public TtsInfo get(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(REST_URL_PREFIX+"/tts/get/"+id,TtsInfo.class);

    }


    @RequestMapping(value = "/consumer/tts/insert", method = RequestMethod.POST)
    public boolean add(@RequestBody TtsInfo ttsinfo)
    {
       return restTemplate.postForObject(REST_URL_PREFIX+"/tts/insert",ttsinfo,Boolean.class);

    }
}
