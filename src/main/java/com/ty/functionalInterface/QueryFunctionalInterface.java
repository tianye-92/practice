package com.ty.functionalInterface;

import com.ty.model.Result;

/**
 * TODO
 *
 * @ClassName QueryFunctionalInterface
 * @Author tianye
 * @Date 2019/3/22 23:52
 * @Version 1.0
 */
@FunctionalInterface
public interface QueryFunctionalInterface {

    Result queryResult(String name, String sex);
}
