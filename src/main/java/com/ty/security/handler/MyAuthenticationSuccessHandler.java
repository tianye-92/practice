package com.ty.security.handler;

import com.ty.security.entity.UserEntityDetails;
import com.ty.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 用户登录成功之后，返回JwtToken
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 获取JwtToken
        String token = jwtTokenUtil.generateToken((UserEntityDetails) authentication.getPrincipal());
        StringBuilder builder = new StringBuilder();
        builder.append("{\n\"msg\":\"");
        builder.append("登录成功Handler");
        builder.append("\",\n");
        builder.append("\"token\":\"");
        builder.append(token);
        builder.append("\"\n}");
        out.write(builder.toString());
        out.flush();
        out.close();
    }
}
