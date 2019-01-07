package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.thinkit"})
@ComponentScan(basePackages= {"com.thinkit"})
public class Consumer_Ability_Service_App {

    public static void main(String[] args) {
        SpringApplication.run(Consumer_Ability_Service_App.class,args);
    }
}
