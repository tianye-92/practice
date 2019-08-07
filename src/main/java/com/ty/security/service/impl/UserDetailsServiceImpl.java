//package com.ty.security.service.impl;
//
//import com.brandslink.cloud.common.entity.UserDetailInfo;
//import com.brandslink.cloud.common.entity.UserEntity;
//import com.brandslink.cloud.user.entity.UserInfo;
//import com.brandslink.cloud.user.mapper.UserInfoMapper;
//import com.ty.utils.RedisUtils;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * 用户service，需要实现UserDetailsService接口，实现通过账号查询用户信息逻辑
// *
// * @ClassName UserEntityService
// * @Author tianye
// * @Date 2019/5/27 17:47
// * @Version 1.0
// */
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Resource
//    private UserInfoMapper userInfoMapper;
//
//    @Resource
//    private RedisUtils redisUtils;
//
//    @Autowired
//    private HttpServletRequest request;
//
//    /**
//     * 在执行登录的过程中，这个方法将根据用户名去查找用户，如果用户不存在，则抛出UsernameNotFoundException异常，否则直接将查到的结果返回
//     *
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 实现通过账号查询用户信息逻辑
//        List<UserInfo> userInfo = userInfoMapper.selectByAccountResult(username);
//
//        if (CollectionUtils.isEmpty(userInfo)) {
//            throw new BadCredentialsException("账号不存在，请重新登录!");
//        }
//
//        UserInfo info = userInfo.get(0);
//
//        // 登录仓库代码
//        String warehouseCode = request.getParameter("warehouseCode");
//        // 登录仓库名称
//        String warehouseName = request.getParameter("warehouseName");
//
//        if (StringUtils.isNotBlank(warehouseCode) && StringUtils.isNotBlank(warehouseName)) {
//            List<String> warehouseCodeList = userInfo.stream().map(UserInfo::getWarehouseCode).distinct().collect(Collectors.toList());
//            if (CollectionUtils.isEmpty(warehouseCodeList) || !warehouseCodeList.contains(warehouseCode)) {
//                throw new BadCredentialsException("没有【" + warehouseName + "】登录权限，请选择其他仓库!");
//            }
//            info.setWarehouseCode(warehouseCode);
//            info.setWarehouseName(warehouseName);
//        }
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(info.getAccount());
//        userEntity.setPassword(info.getPassword());
//        userEntity.setEnabled(info.getEnabled());
//        if (StringUtils.isEmpty(info.getRoleCode())) {
//            userEntity.setAuthorities(new ArrayList<>());
//        } else {
//            userEntity.setAuthorities(userInfo.stream().map(UserInfo::getRoleCode).distinct().map(String::valueOf).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//        }
//
//        UserDetailInfo detailInfo = new UserDetailInfo();
//        BeanUtils.copyProperties(info, detailInfo);
//        detailInfo.setRoleCode(userInfo.stream().map(UserInfo::getRoleCode).distinct().collect(Collectors.joining(",")));
//        detailInfo.setRoleName(userInfo.stream().map(UserInfo::getRoleName).distinct().collect(Collectors.joining(",")));
//        if (StringUtils.isBlank(warehouseCode) && StringUtils.isBlank(warehouseName)) {
//            detailInfo.setWarehouseCode(userInfo.stream().map(UserInfo::getWarehouseCode).distinct().collect(Collectors.joining(",")));
//            detailInfo.setWarehouseName(userInfo.stream().map(m -> {
//                if (StringUtils.isNotBlank(m.getWarehouseName())) {
//                    return m.getWarehouseName().split("-")[0];
//                }
//                return null;
//            }).distinct().collect(Collectors.joining(",")));
//        }
//
//        detailInfo.setPassword(null);
//        redisUtils.set(detailInfo.getAccount(), detailInfo);
//
//        return userEntity;
//    }
//
//}
