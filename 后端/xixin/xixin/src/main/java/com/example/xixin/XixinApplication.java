package com.example.xixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.xixin.mapper")
public class XixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(XixinApplication.class, args);
    }

}
