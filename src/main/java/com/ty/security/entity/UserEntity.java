package com.ty.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户实体类，对应数据库用户表
 *
 * @ClassName UserEntity
 * @Author tianye
 * @Date 2019/5/27 17:19
 * @Version 1.0
 */
public class UserEntity implements UserDetails {


    /**
     * 获取当前用户所具有的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 获取当前用户的密码
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * 获取当前用户的用户名
     * @return
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 当前用户账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     *当前用户是否没有被锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 当前用户授权是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 当前用户账号是否激活
     * @return
     */
    @Override
    public boolean isEnabled() {
        return false;
    }
}
