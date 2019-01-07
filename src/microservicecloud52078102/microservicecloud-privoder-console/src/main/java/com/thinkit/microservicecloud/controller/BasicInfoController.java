package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.BasicInfo;
import com.thinkit.microservicecloud.entities.console.Personal_Certificate;
import com.thinkit.microservicecloud.entities.console.ReturnType;
import com.thinkit.microservicecloud.entities.userlogin.ResultInfo;
import com.thinkit.microservicecloud.service.impl.BasicInfoServiceImpl;
import com.thinkit.microservicecloud.service.impl.PersonalCertificateServiceImpl;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class BasicInfoController {

    private Logger logger = LoggerFactory.getLogger(BasicInfoController.class);


    @Autowired
    private BasicInfoServiceImpl basicInfoService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/provider/console/basicInfo/updateInfo/{session}",method = RequestMethod.POST)
    public ReturnType updateinfo(@RequestBody BasicInfo info, @PathVariable("session") String session){
        ReturnType returnType = new ReturnType();
        selectDb(0);
        String userid =  getValue(session);
        if(userid==null || userid.equals("")){
            returnType.setStatus("1002");
            returnType.setMessage("请重新登陆");
            return returnType;
        }


        returnType.setStatus("1001");
        returnType.setMessage("success");
        try{
            basicInfoService.update_basicinfo(info);
        }catch (Exception e){
            returnType.setStatus("1002");
            returnType.setMessage("fault");
            logger.error(e.getMessage());
        }


        return returnType;
    }


    public  String  getValue(String key){

        return redisTemplate.opsForValue().get(key);

    }

    public void selectDb(int dbindex){

        JedisConnectionFactory jedisConnectionFactory  = ( JedisConnectionFactory )  redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbindex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }


}
