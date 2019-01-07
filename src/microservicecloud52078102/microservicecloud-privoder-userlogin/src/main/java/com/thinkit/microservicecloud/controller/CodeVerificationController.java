package com.thinkit.microservicecloud.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.util.SmsDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CodeVerificationController {
    protected Logger logger  = LoggerFactory.getLogger(CodeVerificationController.class);
    private  String verification;

    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @RequestMapping(value = "/privoder/open/code/verification/{phone}",method = RequestMethod.GET)
    public ResultInfo sendVerificationCode(@PathVariable("phone") String phone){

        ResultInfo resultInfo = new ResultInfo();
        logger.info("开始向用户手机发送验证码 ...");


        String verification = (int)((Math.random()*9+1)*100000)+"";

        //向手机发送验证码
       //  SmsDemo.sendSms(phone,verification);
       try {
           SendSmsResponse response =  SmsDemo.sendSms(phone,verification);

           System.out.println("短信接口返回的数据----------------");
           System.out.println("Code=" + response.getCode());
           System.out.println("Message=" + response.getMessage());
           System.out.println("RequestId=" + response.getRequestId());
           System.out.println("BizId=" + response.getBizId());

           if(!response.getCode().equals("OK")){
               resultInfo.setCode("1002");
               resultInfo.setMsg(response.getMessage());
               return resultInfo;
           }
       }catch (Exception e){
           logger.error(e.getMessage());
       }

         creatVerificationCode(phone,verification);


       resultInfo.setCode("1001");
       resultInfo.setMsg("发送成功");
       return resultInfo;
    }

    /**
     * 验证码生成接口（预留，暂无）
     * @return
     */
    public  String creatVerificationCode(String  phone,String verification){
        logger.info("验证码： "+phone+" --> " + verification);
        setKeyEffective(phone,verification);
        return "验证码已存入redis，60s 内有效";
    }


    public  void  setKeyEffective(String key,String value){
        //默认登陆后code 有效期60s
        redisTemplate.opsForValue().set(key,value,60,TimeUnit.SECONDS);

    }



}
