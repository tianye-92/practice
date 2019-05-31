package com.ty.security.service;

import com.ty.entity.UserEntity;

/**
 * 用户操作接口
 *
 * @ClassName IUserService
 * @Author tianye
 * @Date 2019/5/30 17:09
 * @Version 1.0
 */
public interface IUserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(UserEntity user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

}
