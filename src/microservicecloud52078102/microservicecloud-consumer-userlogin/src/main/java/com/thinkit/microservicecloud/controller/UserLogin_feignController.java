package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.PhoneCode;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.userlogin.UserLogin;
import com.thinkit.microservicecloud.entities.userlogin.UserRegister;
import com.thinkit.microservicecloud.service.IUserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLogin_feignController {
    private Logger looger = LoggerFactory.getLogger(UserLogin_feignController.class);

    @Autowired
    private IUserLoginService service;

    @RequestMapping(value ="/consumer/userRegister", method= RequestMethod.POST)
    public ResultInfo register(@RequestBody UserRegister info){

        looger.info(info.toString());
        return service.register(info);
    }

    @RequestMapping(value = "/consumer/userLogin" , method = RequestMethod.POST)
    public ResultInfo userLogin(@RequestBody UserLogin userLogin){

        looger.info(userLogin.toString());
        return service.userLogin(userLogin);
    }

    @RequestMapping(value = "/consumer/userPhoneLogin" , method = RequestMethod.POST)
    public ResultInfo userPhoneLogin(@RequestBody PhoneCode info){
        looger.info(info.toString());
        return service.userPhoneLogin(info);
    }

    @RequestMapping(value = "/consumer/userloginOut/{session}" , method = RequestMethod.GET)
    public  ResultInfo loginOut(@PathVariable("session") String session){

        looger.info(session);
        return  service.loginOut(session);
    }
}
