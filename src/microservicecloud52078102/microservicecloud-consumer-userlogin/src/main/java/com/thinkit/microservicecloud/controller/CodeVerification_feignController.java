package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeVerification_feignController {
    private Logger looger = LoggerFactory.getLogger(CodeVerification_feignController.class);

    @Autowired
    private IUserLoginService service;

    @RequestMapping(value = "/open/code/verification/{phone}",method = RequestMethod.GET)
    public String sendVerificationCode(@PathVariable("phone") String phone){
       return  service.sendVerificationCode(phone);
    };
}
