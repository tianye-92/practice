package com.ty.security.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 实现自定义 决策管理器
 *
 * @ClassName UrlAccessDecisionManager
 * @Author tianye
 * @Date 2019/5/27 18:02
 * @Version 1.0
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    /**
     * 1.decide方法接收三个参数，其中第一个参数中保存了当前登录用户的角色信息，
     *   第三个参数则是UrlFilterInvocationSecurityMetadataSource中的getAttributes方法传来的，表示当前请求需要的角色（可能有多个）。
     *
     * 2.如果当前请求需要的权限为ROLE_LOGIN则表示登录即可访问，和角色没有关系，此时我需要判断authentication是不是AnonymousAuthenticationToken的一个实例，
     *   如果是，则表示当前用户没有登录，没有登录就抛一个BadCredentialsException异常，登录了就直接返回，则这个请求将被成功执行。
     *
     * 3.遍历collection，同时查看当前用户的角色列表中是否具备需要的权限，如果具备就直接返回，否则就抛异常。
     *
     * 4.这里涉及到一个all和any的问题：假设当前用户具备角色A、角色B，当前请求需要角色B、角色C，
     *   那么是要当前用户要包含所有请求角色才算授权成功还是只要包含一个就算授权成功？我这里采用了第二种方案，即只要包含一个即可。小伙伴可根据自己的实际情况调整decide方法中的逻辑。
     *
     *
     *
     */




    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("===================================判断是否含有权限");
        if(null== collection || collection.size() <=0) {
            return;
        }
        String needRole;
        for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
            needRole = iter.next().getAttribute();


            for(GrantedAuthority ga : authentication.getAuthorities()) {
                if(needRole.trim().equals(ga.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no privilege");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
