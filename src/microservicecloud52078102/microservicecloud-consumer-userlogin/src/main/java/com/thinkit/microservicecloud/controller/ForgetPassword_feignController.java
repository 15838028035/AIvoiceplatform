package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.PhoneCode;
import com.thinkit.microservicecloud.entities.userlogin.PhonePassword;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgetPassword_feignController {
    private Logger looger = LoggerFactory.getLogger(ForgetPassword_feignController.class);

    @Autowired
    private IUserLoginService service;

    @RequestMapping(value = "/user/login/forgetpassword/firstStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo firstStepValidatePhoneCode(@RequestBody PhoneCode info){
        return service.firstStepValidatePhoneCode(info);
    }

    @RequestMapping(value = "/user/login/forgetpassword/secondStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo  secondStepValidatePhoneCode(@RequestBody PhonePassword info){
        return  service.secondStepValidatePhoneCode(info);
    }
}
