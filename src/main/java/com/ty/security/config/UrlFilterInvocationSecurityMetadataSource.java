package com.ty.security.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * FilterInvocationSecurityMetadataSource有一个默认的实现类DefaultFilterInvocationSecurityMetadataSource，
 * 该类的主要功能就是通过当前的请求地址，获取该地址需要的用户角色，我们照猫画虎，自己也定义一个FilterInvocationSecurityMetadataSource
 *
 * @ClassName UrlFilterInvocationSecurityMetadataSource
 * @Author tianye
 * @Date 2019/5/27 17:52
 * @Version 1.0
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    /**
     * 1.一开始注入了MenuService，MenuService的作用是用来查询数据库中url pattern和role的对应关系，
     * 查询结果是一个List集合，集合中是Menu类，Menu类有两个核心属性，一个是url pattern，即匹配规则(比如/admin/**)，还有一个是List,即这种规则的路径需要哪些角色才能访问。
     * <p>
     * 2.我们可以从getAttributes(Object o)方法的参数o中提取出当前的请求url，然后将这个请求url和数据库中查询出来的所有url pattern一一对照，
     * 看符合哪一个url pattern，然后就获取到该url pattern所对应的角色，当然这个角色可能有多个，所以遍历角色，最后利用SecurityConfig.createList方法来创建一个角色集合。
     * <p>
     * 3.第二步的操作中，涉及到一个优先级问题，比如我的地址是/employee/basic/hello,这个地址既能被/employee/**匹配，
     * 也能被/employee/basic/**匹配，这就要求我们从数据库查询的时候对数据进行排序，将/employee/basic/**类型的url pattern放在集合的前面去比较。
     * <p>
     * 4.如果getAttributes(Object o)方法返回null的话，意味着当前这个请求不需要任何角色就能访问，甚至不需要登录。但是在我的整个业务中，
     * 并不存在这样的请求，我这里的要求是，所有未匹配到的路径，都是认证(登录)后可访问，因此我在这里返回一个ROLE_LOGIN的角色，
     * 这种角色在我的角色数据库中并不存在，因此我将在下一步的角色比对过程中特殊处理这种角色。
     * <p>
     * 5.如果地址是/login_p，这个是登录页，不需要任何角色即可访问，直接返回null。
     * <p>
     * 6.getAttributes(Object o)方法返回的集合最终会来到AccessDecisionManager类中，接下来我们再来看AccessDecisionManager类。
     */

//    @Autowired
//    MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final List<String> urls = new ArrayList<>();

    @Value("${system.login.urls}")
    private String loginUrls;

    @Override
    public void afterPropertiesSet() {
        String[] split = loginUrls.split(",");
        urls.addAll(Arrays.asList(split));
    }

    /**
     * 主要责任就是当访问一个url时返回这个url所需要的访问权限
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        if ("/login_p".equals(requestUrl)) {
//            return null;
//        }
//        List<Menu> allMenu = menuService.getAllMenu();
//        for (Menu menu : allMenu) {
//            if (antPathMatcher.match(menu.getUrl(), requestUrl)&&menu.getRoles().size()>0) {
//                List<Role> roles = menu.getRoles();
//                int size = roles.size();
//                String[] values = new String[size];
//                for (int i = 0; i < size; i++) {
//                    values[i] = roles.get(i).getName();
//                }
//                return SecurityConfig.createList(values);
//            }
//        }
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        String requestURI = request.getRequestURI();
        System.out.println("requestURI:" + requestURI);
        // 配置不需要鉴权的url
        for (String url : urls) {
            if (antPathMatcher.match(url.trim(), requestURI)) {
                return null;
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
//        return SecurityConfig.createList("ROLE_ANONYMOUS");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
