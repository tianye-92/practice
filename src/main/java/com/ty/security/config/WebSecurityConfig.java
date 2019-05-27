package com.ty.security.config;

import com.ty.security.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * webSecurity权限配置主类
 *
 * @ClassName WebSecurityConfig
 * @Author tianye
 * @Date 2019/5/27 18:15
 * @Version 1.0
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserEntityService userEntityService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // passwordEncoder(new BCryptPasswordEncoder()) 登录时对加盐加密之后的密码进行比对，
        // 前提是用户注册的时候，密码也需要使用BCryptPasswordEncoder进行加盐加密之后存储数据库
        auth.userDetailsService(userEntityService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置忽略的请求url
        web.ignoring().antMatchers("/index.html", "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
