package com.ty.functionalInterface;

/**
 * 所以
 *
 * @ClassName NumberSumFunctionalInterface
 * @Author tianye
 * @Date 2019/3/22 23:12
 * @Version 1.0
 */
@FunctionalInterface
public interface NumberSumFunctionalInterface<E extends Number> {

    E sum(E e,E e1);
}
