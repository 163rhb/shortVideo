package com.rhb.shortviedo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.rhb.shortviedo.dao")
@SpringBootApplication
public class ShortviedoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortviedoApplication.class, args);
    }

}
