package com.ty.security.handler;

import com.alibaba.fastjson.JSON;
import com.brandslink.cloud.common.constant.UserConstant;
import com.brandslink.cloud.common.entity.Result;
import com.brandslink.cloud.common.entity.UserDetailInfo;
import com.brandslink.cloud.common.entity.UserEntity;
import com.brandslink.cloud.common.entity.response.LoginSuccessResponseDTO;
import com.brandslink.cloud.common.enums.ResponseCodeEnum;
import com.brandslink.cloud.common.utils.MD5;
import com.brandslink.cloud.common.utils.RedisUtils;
import com.brandslink.cloud.common.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 登录成功Handler
 *
 * @ClassName MyAuthenticationSuccessHandler
 * @Author tianye
 * @Date 2019/5/29 10:31
 * @Version 1.0
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // 使用账号+加密的密码+时间戳通过MD5加密生成token
        StringBuilder tokenDetail = new StringBuilder(UserConstant.REDIS_USER_TOKEN_KEY_FIX);
        UserEntity entity = (UserEntity) authentication.getPrincipal();
        String username = entity.getUsername();
        List<String> details = Arrays.asList(username, entity.getPassword());
        String token = MD5.md5Password(tokenDetail.append(StringUtils.join("-", details)).append("-").append(System.currentTimeMillis()).toString());
        // 存入Redis，1小时有效
        entity.setPassword(null);
        redisUtils.set(token, entity, UserConstant.REDIS_USER_TOKEN_KEY_TIMEOUT);
        UserDetailInfo userDetailInfo = (UserDetailInfo) redisUtils.get(username);
        redisUtils.set(username + token, userDetailInfo);
        redisUtils.remove(username);
        LoginSuccessResponseDTO result = new LoginSuccessResponseDTO();
        BeanUtils.copyProperties(userDetailInfo, result);
        result.setToken(token);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=utf-8");
        Utils.print(JSON.toJSONString(new Result(ResponseCodeEnum.RETURN_CODE_100200, result)));
    }
}
