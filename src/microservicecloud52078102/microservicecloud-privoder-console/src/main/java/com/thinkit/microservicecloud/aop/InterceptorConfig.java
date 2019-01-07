package com.thinkit.microservicecloud.aop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptorConfig  implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        logger.info("---------------------开始进入请求地址拦截----------------------------");



        System.out.println("requestURI is "+  httpServletRequest.getRequestURI());
        System.out.println("requestURL is "+  httpServletRequest.getRequestURL());

        String session = (httpServletRequest.getRequestURI().substring(httpServletRequest.getRequestURI().lastIndexOf("/"),httpServletRequest.getRequestURI().length())).replaceAll("/","");

        System.out.println("客户端请求ip： session: "+ httpServletRequest.getRemoteAddr()+ "  "+session);

        //用户登录 使用 db0 , (session,userid)
        selectDb(0);
        String value = getkeyvalue(session);

        if(value==null || "".equals(value)){
            logger.info("用户session不存在，请登录");
            return false;
        }else{
            logger.info("登录鉴权成功，已登录");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("---------------视图渲染之后的操作-------------------------0");
    }


    public   String getkeyvalue(String key){

        ValueOperations<String,String> ops = redisTemplate.opsForValue();
        String value = ops.get(key);
        System.out.println("redis info: get key:"+key+" --> value:"+value);
        return value;

    }


    public void selectDb(int dbindex){

        JedisConnectionFactory jedisConnectionFactory  = ( JedisConnectionFactory )  redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(dbindex);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }
}