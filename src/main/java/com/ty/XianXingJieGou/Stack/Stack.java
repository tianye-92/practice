package com.ty.XianXingJieGou.Stack;

/**
 * 数据结构 --> 栈(接口)
 * @ClassName Stack
 * @Author tianye
 * @Date 2019/3/9 23:07
 * @Version 1.0
 */
public interface Stack<E> {

    /**
     * 获取栈大小
     * @return
     */
    int getSize();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 压栈
     * @param e
     */
    void push(E e);

    /**
     * 出栈
     * @return
     */
    E pop();

    /**
     * 查看栈中最后一个元素
     * @return
     */
    E peek();
}
