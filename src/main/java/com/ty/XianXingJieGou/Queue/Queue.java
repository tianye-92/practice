package com.ty.XianXingJieGou.Queue;

/**
 * 数据结构 --> 队列
 * @ClassName Queue
 * @Author tianye
 * @Date 2019/3/9 23:36
 * @Version 1.0
 */
public interface Queue<E> {

    /**
     * 获取队列size
     * @return
     */
    int getSize();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入队列
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队列
     * @return
     */
    E dequeue();

    /**
     * 获取队列第一个元素
     * @return
     */
    E getFront();

}
