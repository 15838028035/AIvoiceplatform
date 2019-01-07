package com.thinkit;

import com.thinkit.microservicecloud.entities.AuthInfo;
import com.thinkit.microservicecloud.service.impl.AllServiceSelectServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class ApplicationRunnerConfig implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(ApplicationRunnerConfig.class);

    @Autowired
    private  RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private AllServiceSelectServiceImpl allServiceSelectService;

    private  RedisTemplate<Object,Object> template;

    @PostConstruct
    public void init(){
        template = redisTemplate;
    }
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        List<AuthInfo> authInfos = allServiceSelectService.selectFromService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(AuthInfo authInfo: authInfos){
            System.out.println(authInfo.getCreatTime());
            System.out.println(sdf.format(authInfo.getCreatTime()));
            long intervalTime = getIntervalTime(authInfo.getCreatTime(),new Date());
            long nd =  24 * 60 * 60;
            if(intervalTime>=(authInfo.getIndate()*nd)){
                logger.warn("该服务已过期，不再加载至缓存中；secret_key:"+authInfo.getSecret_key());
            }else{
                //正常使用的服务，初始化加载至缓存中

                logger.debug((authInfo.getIndate()*nd-intervalTime)+"");
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("auth_thread",authInfo.getQps());
//                map.put("access_thread",0);
//                map.put("access_sids",new ArrayList<String>());
//                template.opsForHash().putAll(authInfo.getSecret_key(),map);
//                template.expire(authInfo.getSecret_key(),authInfo.getIndate()*nd-intervalTime,TimeUnit.SECONDS);


                template.opsForValue().set(authInfo.getSecret_key(),authInfo.getQps());

                logger.info("正常使用的服务，初始化加载至缓存中 redis ， hset key:"+authInfo.getSecret_key());


            }

        }

    }


    public long getIntervalTime(Date endDate,Date startDate){
        // 获得两个时间的秒时间差异
        long diff = (startDate.getTime() - endDate.getTime())/1000;
        return diff;
    }
}
