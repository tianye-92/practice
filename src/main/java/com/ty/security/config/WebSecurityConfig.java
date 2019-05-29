package com.ty.security.config;

import com.ty.security.handler.AuthenticationAccessDeniedHandler;
import com.ty.security.handler.EntryPointUnauthorizedHandler;
import com.ty.security.handler.MyAuthenticationFailureHandler;
import com.ty.security.handler.MyAuthenticationSuccessHandler;
import com.ty.security.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * webSecurity权限配置主类
 *
 * @ClassName WebSecurityConfig
 * @Author tianye
 * @Date 2019/5/27 18:15
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;

    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    private UrlAccessDecisionManager manager;

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource securityMetadataSource;

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
        http.formLogin()
//                .loginPage("")  // 后台接口，当需要身份认证的时候，springsecurity会跳转到这个url
                .loginProcessingUrl("/testLogin")   // 使用UsernamePasswordAuthenticationFilter这个类处理，第一个过滤器，前端请求的登录url，默认是 login
                .successHandler(myAuthenticationSuccessHandler)   // 登录成功Handler
                .failureHandler(myAuthenticationFailureHandler)   // 登录失败Handler
                .and()  // 连接符
                .authorizeRequests()  // 授权所有request请求
                .antMatchers("/needLogin","/testLogin").permitAll()  // antMatchers配置的所有url不需要授权就可以访问
                .anyRequest().authenticated()  // 其他所有的请求都需要授权才可以访问
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(manager);
                        o.setSecurityMetadataSource(securityMetadataSource);
                        return o;
                    }
                })   // 添加自定义manager和自定义securityMetadataSource
                .and().csrf().disable()     // 关闭 scrf
                .exceptionHandling()
                .authenticationEntryPoint(entryPointUnauthorizedHandler)  // 校验登录Handler
                .accessDeniedHandler(authenticationAccessDeniedHandler);      // 校验权限Handler

//           http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class); // JWT Filter 辅助登录信息
    }
}
