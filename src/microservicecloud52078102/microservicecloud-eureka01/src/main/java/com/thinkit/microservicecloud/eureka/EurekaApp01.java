package com.thinkit.microservicecloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApp01 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApp01.class,args);
    }
}
