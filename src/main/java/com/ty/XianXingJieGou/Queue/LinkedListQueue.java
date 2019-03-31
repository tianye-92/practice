package com.ty.XianXingJieGou.Queue;

import com.ty.XianXingJieGou.LinkedList.LinkedListOfTy;

/**
 * 数据结构 --> 队列(使用链表实现)
 * 使用链表实现队列,时间复杂度是O(N)级别的,因为链表在添加和删除最后一个元素的时候,时间复杂度都是O(N)级别的,
 * 而队列数据结构是尾进首出,所以总体的时间复杂度为O(N)级别的
 * @ClassName LinkedListQueue
 * @Author tianye
 * @Date 2019/3/13 23:29
 * @Version 1.0
 */
public class LinkedListQueue<E> implements Queue<E> {

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
    public void enqueue(E e) {
        list.addFirst(e);
    }

    @Override
    public E dequeue() {
        return list.removeLast();
    }

    @Override
    public E getFront() {
        return list.getLast();
    }
}
