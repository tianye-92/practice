package com.ty.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户service，需要实现UserDetailsService接口，实现通过账号查询用户信息逻辑
 *
 * @ClassName UserEntityService
 * @Author tianye
 * @Date 2019/5/27 17:47
 * @Version 1.0
 */
@Service
@Transactional
public class UserEntityService implements UserDetailsService {

//    @Autowired
//    private UserMapper mapper;


    /**
     * 在执行登录的过程中，这个方法将根据用户名去查找用户，如果用户不存在，则抛出UsernameNotFoundException异常，否则直接将查到的结果返回
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // todo 实现通过账号查询用户信息逻辑
        return null;
    }



}
