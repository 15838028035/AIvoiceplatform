package com.thinkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@EnableEurekaClient
public class UserLogin_Privoder_App {
    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(UserLogin_Privoder_App.class, args);
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }


}
