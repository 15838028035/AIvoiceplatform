package com.thinkit.microservicecloud.controller;

import com.thinkit.microservicecloud.entities.console.*;
import com.thinkit.microservicecloud.service.impl.AppServiceImpl;
import com.thinkit.microservicecloud.service.impl.ProductServiceImpl;
import com.thinkit.microservicecloud.service.impl.ServiceServiceImpl;
import com.thinkit.microservicecloud.util.GenerateAppId;
import com.thinkit.microservicecloud.util.GenerateSessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserAppController {

    private Logger logger  = LoggerFactory.getLogger(UserAppController.class);

    @Autowired
    private AppServiceImpl service;

    @Autowired
    private ServiceServiceImpl serviceService;

    @Autowired
    private ProductServiceImpl pservice;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping(value = "/provider/console/createapp/{session}",method = RequestMethod.POST)
    public ReturnType createUserApp(@RequestBody UserApp info, @PathVariable String session){


        logger.info("this input parameter is " +info.toString());
        logger.info("controller: session- "+session);
        selectDb(0);
        String userid =  getValue(session);
        logger.info("session 对应的 userid is :"+ userid );
        ReturnType returnType = new ReturnType();
        try {
            String appid = GenerateAppId.getAppId();
            info.setAppid(appid);
            info.setAppkey(appid);
            info.setUserid(Integer.parseInt(userid));
            service.createApp(info);

            int id = info.getId();
            // 插入服务数据表

            for (int i : info.getProductIds()) {

                logger.info("get ProductIds: " + i);
                ServiceProduct product = pservice.selectById(i);
                AppService as = new AppService();
                as.setAppid(id);
                as.setProductid(i);
                as.setIndate(product.getDefault_day());
                as.setQps(product.getDefault_qps());
                as.setState("限时免费");
                String skey = "token_" + GenerateSessionId.getSid();
                as.setSecret_key(skey);

                serviceService.createService(as);

                //redis db2 token 计时
                selectDb(2);
                setkeyEffective(skey, as.getServiceid() + "");

            }
        }catch (Exception e){
            logger.error("创建应用异常："+e.getMessage());
            returnType.setStatus("500");
            returnType.setMessage("创建应用异常");
            return returnType;
        }

        returnType.setStatus("200");
        returnType.setMessage("创建应用成功");


        return returnType;
    }



    public  String  getValue(String key){

        return redisTemplate.opsForValue().get(key);

    }


    // key-value  token_sid -- serviceid  3 天
    public void setkeyEffective(String key,String value){

        redisTemplate.opsForValue().set(key,value,3 ,TimeUnit.DAYS);
    }



    @RequestMapping(value = "/provider/console/myapps/{session}",method = RequestMethod.GET)
    public List<MyApps> myapps(@PathVariable("session") String session){

        //用户登录 redis db0(session,userid)
        selectDb(0);
        int userid =   Integer.parseInt(getValue(session));
        return   service.myapps(userid);
    }

    @RequestMapping(value = "/provider/console/appdetail/{session}/{appid}",method = RequestMethod.GET)
    public List<Appdetail> appdetail(@PathVariable("session") String session, @PathVariable("appid") String appid){

          List<Appdetail> list =  service.appdetail(appid);
          if(list!=null){
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              for(Appdetail app: list){
                  String date = app.getCreatTime();
                  app.setCreatTime(date.substring(0,date.length()-2));
              }
          }

          return list;
    }


    @RequestMapping(value = "/provider/console/delapp/{session}/{id}",method = RequestMethod.DELETE)
    public ReturnType delapp(@PathVariable("session") String session, @PathVariable("id") int id){

        ReturnType returnType = new ReturnType();
        try {
            service.delapp(id);
            service.delapp_service(id);
        }catch (Exception e){
            logger.error("删除应用异常："+e.getMessage());
            returnType.setStatus("500");
            returnType.setMessage("删除应用异常");
            return returnType;
        }
        returnType.setStatus("200");
        returnType.setMessage("删除应用成功");

        return returnType;
    }


    public void selectDb(int dbindex){

        JedisConnectionFactory jedisConnectionFactory  = ( JedisConnectionFactory )  redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbindex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }
}
