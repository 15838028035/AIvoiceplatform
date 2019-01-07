package com.thinkit.microservicecloud.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 测试拦截器
 */
@EnableWebMvc
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    InterceptorConfig interceptorConfig(){
        return  new InterceptorConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(interceptorConfig()).addPathPatterns("/privoder/console/**").excludePathPatterns("/privoder/console/login");
    }
}
