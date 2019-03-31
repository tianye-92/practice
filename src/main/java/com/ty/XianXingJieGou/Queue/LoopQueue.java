package com.ty.XianXingJieGou.Queue;

import java.util.Arrays;

/**
 * 数据结构 --> 循环队列(使用数组实现)
 * @ClassName LoopQueue
 * @Author tianye
 * @Date 2019/3/9 23:49
 * @Version 1.0
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    // front: 指向队列中最后一个元素
    // tail: 指向队列中第一个元素
    // size:大小
    private int front, tail, size;

    public LoopQueue(int capacity) {
        // 实际的数组大小为容量大小+1,因为要浪费一个空间,不然无法判断到底是队列满了,还是队列为空
        this.data = (E[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        // 实际的容量为数组大小-1
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    /**
     * 判断循环队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 入队,从循环队列中的末尾存放元素
     * @param e
     */
    @Override
    public void enqueue(E e) {
        //判断是否需要扩容(如果tail+1取余数组大小==front,代表没有可以空间放置新增的元素了)
        if((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        // 队列进队,是在队列末尾存放元素
        data[tail] = e;
        // 维护tail变量, 取余是因为是循环队列
        tail = (tail + 1) % data.length;
        // 维护size变量,循环队列的大小
        size ++;
    }

    /**
     * 出队,从循环队列中的首端取出元素
     * @return
     */
    @Override
    public E dequeue() {
        // 出队首先判断队列是否为空
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空!");
        }
        // 存储需要出队的元素,用于方法返回
        E ret = data[front];
        // 删除出队的元素,取消引用,用于GC可以进行垃圾回收,释放内存
        data[front] = null;
        // 维护front变量,队列出队是从循环队列的首端取出元素,取余是因为是循环队列
        front = (front + 1) % data.length;
        size --;
        // 在出队之后,需要判断队列是否需要进行缩容,如果当前队列中的元素是容量的1/4,并且缩容的容量不为0,就进行缩容
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    /**
     * 数组根据传入的容量进行扩容或缩容
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        // 循环当前队列中所有存在的元素
        for (int i = 0; i < size; i++) {
            // 把当前队列中所有元素,从front开始,全部依次复制到新扩容的数组中去,
            newData[i] = data[(i + front) % data.length];
        }
        // 另一种遍历方式
        // int j = 0;
//        for(int i = front ; i != tail ; i = (i + 1) % data.length){
//            newData[j] = data[i];
//            j++;
//        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        return "LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", front=" + front +
                ", tail=" + tail +
                ", size=" + size +
                ", capacity=" + getCapacity() +
                '}';
    }

    // 测试
    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>(5);

        for (int j = 0; j < 20; j++) {

            queue.enqueue(j);
            System.out.println(queue);

            if (j % 4 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
