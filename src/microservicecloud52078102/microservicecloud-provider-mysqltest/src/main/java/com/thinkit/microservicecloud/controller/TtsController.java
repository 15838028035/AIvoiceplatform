package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.TtsInfo;
import com.thinkit.microservicecloud.service.impl.TtsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TtsController {

    @Autowired
    private TtsServiceImpl service;

    @RequestMapping(value = "/tts/list", method = RequestMethod.GET)
    public List<TtsInfo> list()
    {
        return service.findall();
    }


    @RequestMapping(value = "/tts/get/{id}", method = RequestMethod.GET)
    public TtsInfo get(@PathVariable("id") Long id)
    {
        return service.findbyId(id);
    }


    @RequestMapping(value = "/tts/insert", method = RequestMethod.POST)
    public boolean add(@RequestBody TtsInfo ttsinfo)
    {
        return service.insert(ttsinfo);
    }
}
