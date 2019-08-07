//package com.ty.security.config;
//
//import com.ty.annotation.WebSecurityConfigurerAdapterFlag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfigurationSource;
//
///**
// * webSecurity权限配置主类
// *
// * @ClassName WebSecurityConfig
// * @Author tianye
// * @Date 2019/5/27 18:15
// * @Version 1.0
// */
//@Configuration
//@EnableWebSecurity
//@WebSecurityConfigurerAdapterFlag
////@EnableGlobalMethodSecurity(prePostEnabled=true)   开启注解支持
//public class UserServerWebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsServiceImpl userEntityService;
//
//    @Autowired
//    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//
//    @Autowired
//    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
//
//    @Autowired
//    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;
//
//    @Autowired
//    private UrlAccessDecisionManager manager;
//
//    @Autowired
//    private UrlFilterInvocationSecurityMetadataSource securityMetadataSource;
//
//    @Autowired
//    private TokenAuthenticationFilter tokenAuthenticationFilter;
//
//    @Autowired
//    private CorsConfigurationSource corsConfigurationSource;
//
//    @Autowired
//    private MyLogoutSuccessHandler myLogoutSuccessHandler;
//
//    @Autowired
//    private CommonMethodUtil commonMethodUtil;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userEntityService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        commonMethodUtil.CommonSettingWebSecurity(web);
//    }
//
//    /**
//     * 配置springsecurity过滤器链， 如果配置了自定义鉴权withObjectPostProcessor，使用security的鉴权antMatchers().permitAll()就会失效，两者只能选其一
//     *
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()     // 关闭 scrf
//                .authorizeRequests()  // 授权所有request请求
////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()  // 放行所有Options的请求
////                .antMatchers("/favicon.ico","/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()  // 其他所有的请求都需要授权才可以访问
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(manager);
//                        o.setSecurityMetadataSource(securityMetadataSource);
//                        return o;
//                    }
//                })   // 添加自定义manager和自定义securityMetadataSource，添加自定义鉴权，不需要配antMatchers，登录请求url会被自动拦截，其他不需要授权的url，需要在自定义鉴权里面配。
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)   // 关闭springsecurity 默认session机制
//                .and()
//                .formLogin()
////                .loginPage("")  // 后台接口，当需要身份认证的时候，springsecurity会跳转到这个url
//                .loginProcessingUrl("/testLogin")   // 使用UsernamePasswordAuthenticationFilter这个类处理，第一个过滤器，前端请求的登录url，默认是 login
//                .successHandler(myAuthenticationSuccessHandler)   // 登录成功Handler
//                .failureHandler(myAuthenticationFailureHandler)   // 登录失败Handler
//                .and()
//                .logout().logoutUrl("/logout")
//                .logoutSuccessHandler(myLogoutSuccessHandler)  // 注销
//                .and()
//                .cors().configurationSource(corsConfigurationSource)   // 开启cors跨域支持，默认使用corsConfigurationSource
//                .and()
//                .exceptionHandling()  // 添加自定义异常Handler
//                .accessDeniedHandler(authenticationAccessDeniedHandler)   // 校验权限Handler
//                .and().headers().cacheControl();
//
//        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // 全局 Filter 辅助登录信息
//    }
//
//}
