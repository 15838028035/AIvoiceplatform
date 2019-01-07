package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.thinkit"})
@ComponentScan(basePackages= {"com.thinkit"})
public class Consumer_userlogin_App {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_userlogin_App.class,args);
    }








}
