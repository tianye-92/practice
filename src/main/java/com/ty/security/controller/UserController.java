package com.ty.security.controller;

import com.ty.entity.UserEntity;
import com.ty.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理Controller
 *
 * @ClassName UserController
 * @Author tianye
 * @Date 2019/5/30 17:14
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    @PostMapping(value = "/denglu", params = {"username", "password"})
    public String getToken(String username, String password) {
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     */
    @PostMapping(value = "/zhuce")
    public String register(UserEntity user) {
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     */
    @GetMapping(value = "/shuaxin")
    public String refreshToken(@RequestHeader String authorization) {
        return userService.refreshToken(authorization);
    }

}