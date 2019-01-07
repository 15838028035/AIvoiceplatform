package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) // 阻止springboot 自动加载 datasource
public class Provider_tts_App {

    public static void main(String[] args) {
        SpringApplication.run(Provider_tts_App.class,args);
    }
}
