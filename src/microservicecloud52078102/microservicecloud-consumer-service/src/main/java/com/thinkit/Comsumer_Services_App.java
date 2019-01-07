package com.thinkit;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages= {"com.thinkit"})
@ComponentScan(basePackages= {"com.thinkit"})
public class Comsumer_Services_App {
    public static void main(String[] args) {
        SpringApplication.run(Comsumer_Services_App.class,args);
    }

   /* @Value("${http.port}")
    private Integer port;


    // 这是spring boot 1.5.X以下版本的 添加了这个，下一个就不用添加了
    // Tomcat started on port(s): 20001 (https) 18443 (http)
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http
        return tomcat;
    }


    // 配置http
    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(port);
        return connector;
    }*/
}
