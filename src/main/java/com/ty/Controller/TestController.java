package com.ty.Controller;

import com.alibaba.fastjson.JSON;
import com.ty.annotation.RequestRequire;
import com.ty.entity.TestTy;
import com.ty.functionalInterface.QueryFunctionalInterface;
import com.ty.model.Request;
import com.ty.model.Result;
import com.ty.service.TestService01;
import com.ty.service.TestService02;
import com.ty.service.TestTyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试InitializingBean 以及函数式接口使用
 *
 * @ClassName TestController
 * @Author tianye
 * @Date 2019/3/14 23:17
 * @Version 1.0
 */
@RestController
@Api("swaggerController注解")
public class TestController implements InitializingBean {

    private static Map<Integer, QueryFunctionalInterface> map = new HashMap<>();

    @Autowired
    private TestService01 service01;

    @Autowired
    private TestService02 service02;

    @Autowired
    private TestTyService testTyService;

    @Autowired
    private RestTemplate restTemplatel;

    @RequestRequire(require = "request", parameter = Request.class)
    //@RequestRequire(require = "name,sex",parameter = String.class)
    @PostMapping("/demo")
    @ApiOperation(value = "方法上的swagger注解", notes = "描述")
    public Result demo(@RequestBody Request request) {
//        QueryFunctionalInterface service = map.getOrDefault(request.getInputType(),(a,b)->new Result());
//        Result queryResult = service.queryResult(request.getName(), request.getSex());
//        System.out.println("返回结果");

        QueryFunctionalInterface serviceQuery = (a, b) -> new Result();
        if (1 == request.getInputType())
            serviceQuery = service01::getResult01;
        else if (2 == request.getInputType())
            serviceQuery = service02::getResult02;

        Result result = serviceQuery.queryResult(request.getName(), request.getSex());

        System.out.println("返回结果");
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(1, service01::getResult01);
        map.put(2, service02::getResult02);
    }

    @PostMapping("/testDate")
    public List<TestTy> get() {
        return testTyService.get();
    }

    @PostMapping("/add")
    //    @AspectContrLog(descrption = "测试事务", actionType = "ADD")
    public void get(@RequestBody TestTy testTy) {
        testTyService.modify(testTy);
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String update() {
//        System.out.println("=============test=================");
//        Map<String, String> map = new HashMap<>();
//        map.put("customerAppId", "101175368023");
//        map.put("sign", "f5d889902b28909b591812fad2a4a254");
////        map.put("roleName", "roleName");
////        map.put("warehouseName", "warehouseName");
////        map.put("warehouseCode", "warehouseCode");
//        ResponseEntity<String> response = restTemplatel.getForEntity("http://localhost:8192/role/getRoleListByWarehouseCode?customerAppId={customerAppId}&sign={sign}", String.class, map);
//        String body = response.getBody();
//        return body;
//    }

    public class Demo {

        private String customerAppId;

        private String sign;

        private String page;

        private String row;

        private String roleName;

        public String getCustomerAppId() {
            return customerAppId;
        }

        public void setCustomerAppId(String customerAppId) {
            this.customerAppId = customerAppId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getRow() {
            return row;
        }

        public void setRow(String row) {
            this.row = row;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String update() {
//        System.out.println("=============test=================");
////        Map<String, String> map = new HashMap<>();
////        map.put("customerAppId", "101175368023");
////        map.put("sign", "5fa8b369b8fef3290d1595b9003acdab");
////        map.put("page", "1");
////        map.put("row", "10");
////        map.put("roleName", "超级管理员");
//        Demo demo = new Demo();
//        demo.setCustomerAppId("101175368023");
//        demo.setSign("5fa8b369b8fef3290d1595b9003acdab");
//        demo.setPage("1");
//        demo.setRow("10");
//        demo.setRoleName("超级管理员");
//        ResponseEntity<String> response = restTemplatel.postForEntity("http://localhost:8192/role/getRoleList", JSON.toJSONString(demo), String.class);
//        String body = response.getBody();
//        return body;
//    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String update() {
        System.out.println("=============test=================");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json;charset=UTF-8");
//        requestHeaders.add("token", "b219ea5ed200cc2c500cba9670046b6c");
        Map<String, String> map = new HashMap<>();
        map.put("customerAppId", "100289893020");
        map.put("sign", "5fa8b369b8fef3290d1595b9003acdab");
        map.put("page", "1");
        map.put("row", "10");
        map.put("roleName", "超级管理员");
        HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(map), requestHeaders);
        ResponseEntity<String> response = restTemplatel.postForEntity("http://localhost:8192/role/getRoleList?customerAppId={customerAppId}&sign={sign}", requestEntity, String.class, map);
        String body = response.getBody();
        return body;
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update2() {
        System.out.println("=============update=================");
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        return "update";
    }

    @RequestMapping(value = "/needLogin", method = RequestMethod.GET)
    public String update3() {
        System.out.println("=============needLogin=================");
        return "needLogin";
    }


    //    @PreAuthorize("hasRole('ADMIN')")
//    @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
    @PreAuthorize("hasAnyRole('admin','user')")
    @RequestMapping(value = "/testLogin", method = RequestMethod.GET)
    public String testLogin() {
        System.out.println("=============testLogin=================");
        return "login success";
    }

    public static void main(String[] args) {

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/role/**", "/role/menu/dsa"));
    }
}
