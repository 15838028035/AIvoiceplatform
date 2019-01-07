package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Provider_console_app {
    public static void main(String[] args) {
        SpringApplication.run(Provider_console_app.class,args);
    }
}
