package com.ty.security.config;

import com.ty.security.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserEntityService userEntityService;

//    @Value("${system.ignore.login.url}")
//    private String ignore;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {


        /*String[] uri = ignore.split(",");
            for (String s : uri) {
                if (new AntPathRequestMatcher(s.trim()).matches(request)) {
                    response.getWriter().write("matcher");
                    return;
                }
            }*/

        List list = new ArrayList();
        SimpleGrantedAuthority ga = new SimpleGrantedAuthority("admin");
        list.add(ga);
        UserDetails userDetails = userEntityService.loadUserByUsername("tianye");
                UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, list);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(false){
            response.getWriter().write("inreup");
            return;
        }
        chain.doFilter(request, response);
    }
}
