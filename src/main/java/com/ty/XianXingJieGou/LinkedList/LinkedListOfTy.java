package com.ty.XianXingJieGou.LinkedList;

import com.ty.TyShujujiegouApplication;

/**
 * 数据结构 --> 单向链表    JDK中LinkedList是双向链表
 * {@link TyShujujiegouApplication}
 * @ClassName LinkedListOfTy
 * @Author tianye
 * @Date 2019/3/12 22:50
 * @Version 1.0
 */
public class LinkedListOfTy<E> {

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

    // 虚拟头节点
    private Node dummyHead;
    // 维护链表中元素的个数
    private int size;

    // 构造函数中定义一个空的虚拟头节点
    public LinkedListOfTy() {
        dummyHead = new Node(null,null);
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在链表的index(0-based)位置添加新的元素e
    // 在链表中不是一个常用的操作，练习用：）
    public void add(int index,E e){
        Node prev = getNode(index);
        // 下面两种写法效果一样
//        Node node = new Node(e);
//        node.next = prev.next;
//        prev.next = node;
        prev.next = new Node(e,prev.next);
        size++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0,e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size,e);
    }

    // 获得链表的第index(0-based)个位置的元素
    // 在链表中不是一个常用的操作，练习用：
    public E get(int index){
        return getNextNode(index).e;
    }

    // 获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改链表的第index(0-based)个位置的元素为e
    // 在链表中不是一个常用的操作，练习用：
    public void set(int index,E e){
        getNextNode(index).e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node node = dummyHead.next;
        while (node != null){
            if (node.e.equals(e)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用：
    public E remove(int index){
        Node node = getNode(index);
        // 获取需要删除的节点
        Node resNode = node.next;
        // 需要删除节点的前一个节点的next 等于 需要删除节点的next 而不等于删除节点了,这样就把需要删除的节点删除了
        node.next = resNode.next;
        // 需要删除的节点置为 null
        resNode.next = null;
        size --;
        return resNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e){
        // 以下两种写法效果一致
        for (Node node = dummyHead; node.next != null; node = node.next){
            if (node.next.e.equals(e)){
                Node delNode = node.next;
                node.next = delNode.next;
                delNode.next = null;
                size --;
                break;
            }
        }
//        Node prev = dummyHead;
//        while(prev.next != null){
//            if(prev.next.e.equals(e))
//                break;
//            prev = prev.next;
//        }
//        Node delNode = prev.next;
//        prev.next = delNode.next;
//        delNode.next = null;
//        size --;

    }

    /**
     * 根据index获取对应的node节点,从dummyHead.next(第一个元素节点)开始
     * @param index
     * @return
     */
    private Node getNextNode(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("参数:"+index+",不合法.");
        }
        // 从第一个元素开始遍历
        Node cur = dummyHead.next;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 根据index获取对应的node节点,从dummyHead(虚拟头节点)开始
     * @param index
     * @return
     */
    private Node getNode(int index){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("参数:"+index+",不合法.");
        }
        Node node = dummyHead;
        // 从虚拟头节点开始遍历,遍历到需要删除节点的前一个节点
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur).append("->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    // 测试
    public static void main(String[] args) {

        LinkedListOfTy<Integer> linkedList = new LinkedListOfTy<>();
        for(int i = 0 ; i < 5 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeElement(1);
        System.out.println(linkedList);
    }
}
