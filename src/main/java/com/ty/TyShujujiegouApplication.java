package com.ty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
//@MapperScan("com.ty.mapper")
public class TyShujujiegouApplication extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/rest/api/doc/**").addResourceLocations("classpath:/swagger/dist/");
    }

    public static void main(String[] args) {
        SpringApplication.run(TyShujujiegouApplication.class, args);
    }

}
