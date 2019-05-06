package com.ty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@MapperScan("com.ty.mapper")
public class TyShujujiegouApplication {

    public static void main(String[] args) {
        SpringApplication.run(TyShujujiegouApplication.class, args);
    }

}
