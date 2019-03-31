package com.ty.service.impl;

import com.ty.model.Result;
import com.ty.service.TestService01;
import com.ty.service.TestService02;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @ClassName TestService01Impl
 * @Author tianye
 * @Date 2019/3/22 23:58
 * @Version 1.0
 */
@Service
public class TestService02Impl implements TestService02 {

    @Override
    public Result getResult02(String name,String sex) {
        System.out.println("进入test02实现类");
        Result result = new Result();
        result.setName(name);
        result.setSex(sex);
        return result;
    }
}
