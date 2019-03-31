package com.ty.XianXingJieGou.Stack;

import com.ty.XianXingJieGou.LinkedList.LinkedListOfTy;

/**
 * 数据结构 --> 栈(使用链表实现栈)
 * 使用链表实现栈,时间复杂度是O(1)级别的,因为链表在添加和删除第一个元素的时候,时间复杂度都是O(1)级别的
 * @ClassName LinkedListStack
 * @Author tianye
 * @Date 2019/3/13 23:33
 * @Version 1.0
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedListOfTy<E> list;

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }
}
