package com.ty.XianXingJieGou.Queue;


/**
 * 数据结构 --> 队列(使用双向链表实现)
 * 使用双向链表实现队列,时间复杂度是O(1)级别的,因为双向链表在添加和删除第一个和最后一个元素的时候,时间复杂度都是O(1)级别的
 * @ClassName LinkedListQueue
 * @Author tianye
 * @Date 2019/3/13 23:29
 * @Version 1.0
 */
public class LinkedListTwoQueue<E> implements Queue<E> {

    // 内部类,链表中的具体节点(元素)
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private int size;
    private Node first,last;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队从链表尾端入
     * @param e
     */
    @Override
    public void enqueue(E e) {
        // 如果链表尾端为空,那么链表肯定是一个空链表,首端也是空的.
        if(last == null){
            last = new Node(e);
            first = last;
        }else {
            //如果不为空,在链表尾端插入
            last.next = new Node(e);
            last = last.next;
        }
        size++;
    }

    /**
     * 出队从链表首端出
     */
    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("链表为空,不能执行dequeue操作!");
        }
        Node resNode = first;
        first = first.next;
        resNode.next = null;
        // 出队操作执行完需要判断下当前链表是否为空
        if (first == null){
            // 如果链表为空,首元素和尾元素都应该为null
            last = null;
        }
        size --;
        return resNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("链表为空,不能执行getFront操作!");
        }
        return first.e;
    }
}
