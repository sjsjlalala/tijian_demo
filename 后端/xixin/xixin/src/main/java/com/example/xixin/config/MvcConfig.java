package com.example.xixin.config;

import com.example.xixin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: xixin
 * @package: com.example.xixin.config
 * @className: MvcConfig
 * @author: moki
 * @description: TODO
 * @date: 2024/6/13 12:22
 * @version: 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginInterceptor())
               .addPathPatterns("/**")
               .excludePathPatterns("/users/login","/users/register");
    }
}
