package com.ty.test;

import com.ty.Number.BigDecimalTest;
import com.ty.functionalInterface.NumberSumFunctionalInterface;

import java.math.BigDecimal;

/**
 * 测试类
 *
 * @ClassName Test
 * @Author tianye
 * @Date 2019/3/22 23:17
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("10");
        BigDecimal bigDecimal1 = new BigDecimal("20");

        NumberSumFunctionalInterface<BigDecimal> numberSum = BigDecimalTest::overrideAdd;

        BigDecimal sum = numberSum.sum(bigDecimal, bigDecimal1);

        System.out.println(sum);

        NumberSumFunctionalInterface<Integer> integerSum = Integer::sum;

        Integer integer = integerSum.sum(10, 20);

        System.out.println(integer);


    }
}
