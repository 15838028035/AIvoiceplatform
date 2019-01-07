package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.userlogin.*;
import com.thinkit.microservicecloud.service.impl.BasicInfoServiceImpl;
import com.thinkit.microservicecloud.service.impl.UserCurdServiceImpl;
import com.thinkit.microservicecloud.service.impl.UserValidateServiceImpl;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserLoginController {

    private Logger logger = LoggerFactory.getLogger(UserLoginController.class);


    @Autowired
    private RedisTemplate<String ,String>  redisTemplate;

    @Autowired
    private UserCurdServiceImpl service;

    @Autowired
    private UserValidateServiceImpl phoneService;


     /**
     * 注册
     */
     @RequestMapping(value ="/privoder/userRegister", method= RequestMethod.POST)
    public ResultInfo register(@RequestBody UserRegister info){

         logger.info(info.toString());
         ResultInfo resultInfo = new ResultInfo();

         // 空项校验
        if("".equals(info.getUsername()) || "".equals(info.getPassword()) ||"".equals(info.getPhone())|| "".equals(info.getEmail())||
                "".equals(info.getCode())){
            resultInfo.setCode("1002");
            resultInfo.setMsg("注册信息不能有空项");
            logger.info("注册信息不能有空项");
            return  resultInfo;
        }

        // 校验手机验证码是否正确 redis : phone-->code映射


         String value = getkeyvalue(info.getPhone());
         if(value==null || "".equals(value)){
             // 验证失败：验证码过期
             resultInfo.setCode("1002");
             resultInfo.setMsg("验证失败：验证码过期");
             logger.info("验证失败：验证码过期");
             return  resultInfo;
         }else if(!value.equals(info.getCode())){
             // 验证失败：验证码错误
             resultInfo.setCode("1002");
             resultInfo.setMsg("验证失败：验证码错误");
             logger.info("验证失败：验证码错误");
         }

         if(value.equals(info.getCode())){
             //验证通过
             logger.info("手机验证码验证通过，开始注册进入数据库");
             resultInfo.setCode("1001");
             resultInfo.setMsg("验证通过");
             resultInfo.setResult("记录数据库");

          /*   //设置缓存
             setKeyEffective(info.getPhone(),info.getEmail());*/

             //     插入数据库用户注册信息( 同时记录到 user表 和 basic_info表)
            service.insert(info);

           /*   spring 事务，目的是让spring去处理异常，这样操作才能终止或者回滚，不然自己去捕获异常，事务是不生效的
           try{

           }catch (Exception e){
               logger.error("insert basicinfo error: "+e.getMessage());
           }*/

         }


         return resultInfo;

    }


    public String getkeyvalue(String key){
        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        logger.info("redis info: get key:"+key+" --> value:"+value);
        return value;

    }



    public  void  setKeyEffective(String key,String value){
         //默认登陆后session 有效期10分钟
         redisTemplate.opsForValue().set(key,value,600,TimeUnit.SECONDS);

     }



    /**
     *  用户名/邮箱 登陆
     * @param userLogin
     * @return
     */
     @RequestMapping(value = "/privoder/userLogin" , method = RequestMethod.POST)
     public ResultInfo userLogin(@RequestBody  UserLogin userLogin){

           logger.info(userLogin.toString());

           ResultInfo resultInfo = new ResultInfo();
           List<User> list = service.userLogin(userLogin);

           logger.info("匹配用户信息，size: "+list.size());
           if(list.size()==1){
               logger.info("登陆成功");
               resultInfo.setCode("1001");
               resultInfo.setMsg("登陆成功");

               // 登陆成功后，redis 记录 session --> userid 映射
              String session =  GenerateSessionId.getSid();
              setKeyEffective(session,list.get(0).getUserid()+"");

              logger.info(" session: "+session +" 记录到redis中,有效期：600s");
              resultInfo.setLogin(session);

           }else{
               logger.info("登陆失败");
               resultInfo.setCode("1002");
               resultInfo.setMsg("登陆失败");
           }


           return resultInfo;
     }


    /**
     *  手机号 登陆
     * @param info
     * @return
     */
    @RequestMapping(value = "/privoder/userPhoneLogin" , method = RequestMethod.POST)
    public ResultInfo userPhoneLogin(@RequestBody PhoneCode info){

        logger.info(info.toString());
        ResultInfo resultInfo = new ResultInfo();


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


           List<User> list = phoneService.selectByPhone(info.getPhone());
            // 登陆成功后，redis 记录 session --> userid 映射
            String session =  GenerateSessionId.getSid();


            setKeyEffective(session,list.get(0).getUserid()+"");

            logger.info("redisdb 1 , session: "+session +" 记录到redis中,有效期：600s");
            resultInfo.setLogin(session);
        }

        return resultInfo;
    }

    @RequestMapping(value = "/privoder/userloginOut/{session}" , method = RequestMethod.GET)
    public  ResultInfo loginOut(@PathVariable String session){
        //用户退出，清理session
        ResultInfo resultInfo = new ResultInfo();
        delKey(session);
        resultInfo.setCode("1001");
        resultInfo.setMsg("清除session");

        return resultInfo;
    }


    public  void  delKey(String key){
        //默认登陆后session 有效期10分钟
        redisTemplate.delete(key);

    }




}
