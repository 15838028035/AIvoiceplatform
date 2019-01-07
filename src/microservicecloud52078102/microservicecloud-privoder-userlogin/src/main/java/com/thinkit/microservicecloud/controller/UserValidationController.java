package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.EmailValidate;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.service.impl.UserValidateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserValidationController {

    Logger logger = LoggerFactory.getLogger(UserValidationController.class);

    @Autowired
    private UserValidateServiceImpl service;

    @RequestMapping(value = "/privoder/userinfo/phone/{phone}",method = RequestMethod.GET)
    public ResultInfo validatePhone(@PathVariable("phone") String phone){

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");
        resultInfo.setLogin("");
        resultInfo.setMsg("");
        resultInfo.setResult("通过");
        List<User>  list= service.selectByPhone(phone);
        if(list.size()!=0){
            resultInfo.setResult("未通过");
        }

        return resultInfo;
    }


    @RequestMapping(value = "/privoder/userinfo/email",method = RequestMethod.POST)
    public ResultInfo validateEmail(@RequestBody EmailValidate info){

        logger.info("输入参数：email "+ info.getEmail());
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");
        resultInfo.setLogin("");
        resultInfo.setMsg("");
        resultInfo.setResult("通过");
        List<User>  list= service.selectByEmail(info.getEmail());
        if(list.size()!=0){
            resultInfo.setResult("未通过");
        }

        return resultInfo;
    }


    @RequestMapping(value = "/privoder/userinfo/username/{username}",method = RequestMethod.GET)
    public ResultInfo validateUserName(@PathVariable("username") String username){

        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode("1001");
        resultInfo.setLogin("");
        resultInfo.setMsg("");
        resultInfo.setResult("通过");
        List<User>  list= service.selectByUserName(username);
        if(list.size()!=0){
            resultInfo.setResult("未通过");
        }

        return resultInfo;
    }

}
