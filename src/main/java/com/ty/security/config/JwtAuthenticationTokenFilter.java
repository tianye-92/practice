package com.ty.security.config;

import com.ty.security.service.UserEntityService;
import com.ty.utils.JwtTokenUtil;
import com.ty.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

//    @Value("${system.ignore.login.url}")
//    private String ignore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        // 判断是否为 ajax 预请求
//        if (CorsUtils.isCorsRequest(request)) {
//
//        }
        // 获取token
        String authHeader = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userEntityService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
//        if (null != request){
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            String s = "登录超时，请重新登录！";
//            Utils.print(s);
//            return;
//        }
        // 继续执行后面的过滤器链
        chain.doFilter(request, response);
    }
}
