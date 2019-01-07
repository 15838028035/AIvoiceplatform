package com.thinkit.microservicecloud.controller;


import com.thinkit.microservicecloud.entities.onlineasr.VoiceRec;
import com.thinkit.microservicecloud.entities.voiceprint.VoiceResult;
import com.thinkit.microservicecloud.service.XmlUtil;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class t_controller {

    private Logger logger = LoggerFactory.getLogger(t_controller.class);

    @RequestMapping(value = "/https/test", method = RequestMethod.POST)
    public String rec() {


        logger.info("welcome https request.");



        return "welcome https request.";
    }

    @RequestMapping(value = "/https/test/get", method = RequestMethod.GET)
    public String rec3() {


        logger.info("welcome https request.");



        return "welcome https request.";
    }




}
