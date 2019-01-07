package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.EmailValidate;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserValidation_feignController {

    private Logger looger = LoggerFactory.getLogger(UserValidation_feignController.class);

    @Autowired
    private IUserLoginService service;

    @RequestMapping(value = "/consumer/userinfo/phone/{phone}",method = RequestMethod.GET)
    public ResultInfo validatePhone(@PathVariable("phone") String phone){

        return service.validatePhone(phone);
    }

    @RequestMapping(value = "/consumer/userinfo/email",method = RequestMethod.POST)
    public ResultInfo validateEmail(@RequestBody EmailValidate info){
        return  service.validateEmail(info.getEmail());
    }

    @RequestMapping(value = "/consumer/userinfo/username/{username}",method = RequestMethod.GET)
    public ResultInfo validateUserName(@PathVariable("username") String username){
        return service.validateUserName(username);
    }
}
