package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.thinkit"})
@ComponentScan(basePackages= {"com.thinkit"})
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Consumer_console_App {
    public static void main(String[] args) {
        SpringApplication.run(Consumer_console_App.class,args);
    }
}
