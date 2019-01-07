package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.PhoneCode;
import com.thinkit.microservicecloud.entities.userlogin.PhonePassword;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.entities.userlogin.User;
import com.thinkit.microservicecloud.service.impl.UserCurdServiceImpl;
import com.thinkit.microservicecloud.service.impl.UserValidateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ForgetPasswordController {

    private Logger logger = LoggerFactory.getLogger(ForgetPasswordController.class);
    @Autowired
    private RedisTemplate<String ,String> redisTemplate;

    @Autowired
    private UserCurdServiceImpl service;

    @Autowired
    private UserValidateServiceImpl validateService;

    @RequestMapping(value = "/privoder/user/login/forgetpassword/firstStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo  firstStepValidatePhoneCode(@RequestBody PhoneCode info){

        logger.info(info.toString());
        ResultInfo resultInfo = new ResultInfo();

        selectDb(0);
        String value = getkeyvalue(info.getPhone());
        if(value==null || "".equals(value)){
            logger.info("验证码过期了");

            resultInfo.setCode("1002");
            resultInfo.setMsg("验证码过期了");

        }else if(!value.equals(info.getCode())){
            logger.info("验证码错误");

            resultInfo.setCode("1002");
            resultInfo.setMsg("验证码错误");

        }else if(value.equals(info.getCode())){
            logger.info("验证通过");
            resultInfo.setCode("1001");
            resultInfo.setMsg("验证通过");
        }

        return resultInfo;
    }


    public String getkeyvalue(String key){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        logger.info("redis info: get key:"+key+" --> value:"+value);
        return value;

    }


    @RequestMapping(value = "/privoder/user/login/forgetpassword/secondStepValidatePhoneCode",method = RequestMethod.POST)
    public ResultInfo  secondStepValidatePhoneCode(@RequestBody PhonePassword info){

        logger.info(info.toString());
        ResultInfo resultInfo = new ResultInfo();

        List<User> list = validateService.selectByPhone(info.getPhone());
        if(list.size()==0){
            resultInfo.setCode("1002");
            resultInfo.setMsg("无此手机号");
            return resultInfo;
        }else if(list.size()>1){
            resultInfo.setCode("1002");
            resultInfo.setMsg("error: 拥有此手机号的用户存在多个，数据混乱");
            return resultInfo;
        }

        //直接重置新密码
        service.resetPassword(info);
        resultInfo.setCode("1001");
        resultInfo.setMsg("");

        return resultInfo;

    }

    public void selectDb(int dbindex){

        JedisConnectionFactory jedisConnectionFactory  = ( JedisConnectionFactory )  redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbindex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }
}
