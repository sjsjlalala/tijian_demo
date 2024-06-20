package com.example.tianjian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.tianjian.mapper")
public class TianjianApplication {

    public static void main(String[] args) {
        SpringApplication.run(TianjianApplication.class, args);
    }

}
