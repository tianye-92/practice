package com.ty.security.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录及注销
 *
 * @ClassName LoginController
 * @Author tianye
 * @Date 2019/6/14 9:39
 * @Version 1.0
 */
@RestController
@Api("登录及注销相关接口")
public class LoginController {

    @ApiOperation("登录接口")
    @PostMapping("/testLogin")
    public void login(@ApiParam(name = "username", value = "账号", required = true) @RequestParam("username") String username,
                      @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
                      @ApiParam(name = "warehouseCode", value = "所属仓库代码") @RequestParam(value = "warehouseCode", required = false) String warehouseCode,
                      @ApiParam(name = "warehouseName", value = "所属仓库名称") @RequestParam(value = "warehouseName", required = false) String warehouseName) {
        System.out.println("使用springSecurity做用户登录认证!");
    }

    @ApiOperation("注销接口")
    @GetMapping("/logout")
    public void logout() {
        System.out.println("使用springSecurity做用户注销!");
    }

}
