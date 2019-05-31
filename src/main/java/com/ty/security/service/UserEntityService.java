package com.ty.security.service;

import com.ty.entity.UserEntity;
import com.ty.mapper.UserEntityMapper;
import com.ty.security.entity.UserEntityDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户service，需要实现UserDetailsService接口，实现通过账号查询用户信息逻辑
 *
 * @ClassName UserEntityService
 * @Author tianye
 * @Date 2019/5/27 17:47
 * @Version 1.0
 */
@Component
public class UserEntityService implements UserDetailsService {

    @Autowired
    private UserEntityMapper userEntityMapper;


    /**
     * 在执行登录的过程中，这个方法将根据用户名去查找用户，如果用户不存在，则抛出UsernameNotFoundException异常，否则直接将查到的结果返回
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo 实现通过账号查询用户信息逻辑
        UserEntity userEntity = userEntityMapper.selectByUsername(username);

        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        // 处理用户权限
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (StringUtils.isNotBlank(userEntity.getRoles())){
            String[] split = userEntity.getRoles().split(",");
            List<String> list = Arrays.asList(split);
            authorityList = list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }

        return new UserEntityDetails(userEntity.getUsername(), userEntity.getPassword(), authorityList);
    }

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("123456789"));

    }

}
