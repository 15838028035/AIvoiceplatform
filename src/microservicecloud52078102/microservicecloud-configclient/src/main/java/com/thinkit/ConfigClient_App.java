package com.thinkit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConfigClient_App {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient_App.class,args);
    }

    @Value("${info.name}")
    private  String name;

    @Value("${info.age}")
    private  String age;

    @RequestMapping(value = "/test")
    public String test(){
        return "姓名："+name+" , 年龄："+age;
    }
}
