package com.ty.security.handler;

/**
 * TODO
 *
 * @ClassName EntryPointUnauthorizedHandler
 * @Author tianye
 * @Date 2019/5/29 17:38
 * @Version 1.0
 */

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定401返回值，自定义了身份验证失败的返回值
 *
 * @author hackyo
 * Created on 2017/12/9 20:10.
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"身份验证失败，请联系管理员!\"}");
        out.flush();
        out.close();
    }

}

