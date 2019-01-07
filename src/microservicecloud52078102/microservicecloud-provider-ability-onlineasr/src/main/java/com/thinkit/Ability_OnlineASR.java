package com.thinkit;

import com.alibaba.fastjson.JSON;
import com.thinkit.microservicecloud.entities.TokenJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableEurekaClient
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})  //阻止spring boot自动注入dataSource bean
public class Ability_OnlineASR {


    public static void main(String[] args) {


        SpringApplication.run(Ability_OnlineASR.class,args);


        }

}
