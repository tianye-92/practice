package com.ty.Controller;

import com.ty.annotation.AspectContrLog;
import com.ty.annotation.RequestRequire;
import com.ty.entity.TestTy;
import com.ty.functionalInterface.QueryFunctionalInterface;
import com.ty.model.Request;
import com.ty.model.Result;
import com.ty.model.TestRequest;
import com.ty.service.TestService01;
import com.ty.service.TestService02;
import com.ty.service.TestTyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
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

    private static Map<Integer , QueryFunctionalInterface> map = new HashMap<>();

    @Autowired
    private TestService01 service01;

    @Autowired
    private TestService02 service02;

    @Autowired
    private TestTyService testTyService;

    @RequestRequire(require = "request",parameter = Request.class)
    //@RequestRequire(require = "name,sex",parameter = String.class)
    @PostMapping("/demo")
    @ApiOperation(value = "方法上的swagger注解", notes = "描述")
    public Result demo(@RequestBody Request request){
//        QueryFunctionalInterface service = map.getOrDefault(request.getInputType(),(a,b)->new Result());
//        Result queryResult = service.queryResult(request.getName(), request.getSex());
//        System.out.println("返回结果");

        QueryFunctionalInterface serviceQuery = (a,b) -> new Result();
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
        map.put(1,service01::getResult01);
        map.put(2,service02::getResult02);
    }

    @PostMapping("/testDate")
    public List<TestTy> get(){
        return testTyService.get();
    }

    @PostMapping("/add")
    //    @AspectContrLog(descrption = "测试事务", actionType = "ADD")
    public void get(@RequestBody TestTy testTy){
        testTyService.modify(testTy);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String update() {
        System.out.println("=============test=================");
        return "test";
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
}
