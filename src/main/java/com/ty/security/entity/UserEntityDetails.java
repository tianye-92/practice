package com.ty.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用于springsecurity用户实体类，实现UserDetails
 *
 * @ClassName UserEntity
 * @Author tianye
 * @Date 2019/5/27 17:19
 * @Version 1.0
 */
public class UserEntityDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public UserEntityDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, authorities, true, true, true, true);
    }

    public UserEntityDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    /**
     * 获取当前用户所具有的角色
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 获取当前用户的密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取当前用户的用户名
     *
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 当前用户账号是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    /**
     * 当前用户是否没有被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    /**
     * 当前用户授权是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    /**
     * 当前用户账号是否激活
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("D", 1);
        map.put("B", 3);
        map.put("C", 2);
        map.put("A", 1);
        map.put("E", 2);

        Collection<Integer> values = map.values();

        List<Integer> collect = values.stream().sorted(Integer::compare).collect(Collectors.toList());

        List<Integer> resultList = values.stream().filter(v -> v.equals(collect.get(0))).collect(Collectors.toList());

        List<String> strings = map.keySet().stream().filter(m -> resultList.contains(map.get(m))).sorted(String::compareTo).collect(Collectors.toList());

        strings.forEach(System.out::println);

    }
}
