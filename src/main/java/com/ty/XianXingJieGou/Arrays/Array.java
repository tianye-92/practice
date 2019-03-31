package com.ty.XianXingJieGou.Arrays;

import java.util.Arrays;

/**
 * 数据结构 --> 数组
 * @ClassName Array
 * @Author tianye
 * @Date 2019/3/9 22:12
 * @Version 1.0
 */
public class Array<E> {

    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array(){
        this(10);
    }

    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index,E e){
        //检查入参合法性
        if(index < 0 || index > size){
            throw new IllegalArgumentException("传入的参数:"+index+"不合法!");
        }
        //判断数组空间是否足够
        if(size == data.length){
            resize(data.length * 2);
        }
        //从数组最后开始复制前一个元素,知道index位置为止
//        for (int i = size-1; i >= index; i--){
//            data[i+1] = data[i];
//        }
        if (size - index >= 0){
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        //把参数e放到index位置
        data[index] = e;
        //维护size变量
        size++;
    }

    /**
     * 数组根据传入的容量进行扩容或缩容
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
//        for (int i = 0; i < size; i++){
//            newData[i] = data[i];
//        }
        if (size >= 0){
            System.arraycopy(data, 0, newData, 0, size);
        }
        data = newData;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e){
        add(size,e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e){
        add(0,e);
    }

    // 获取index索引位置的元素
    public E get(int index){
        //检查入参合法性
        if(index < 0 || index > size){
            throw new IllegalArgumentException("传入的参数:"+index+"不合法!");
        }
        return data[index];
    }

    // 获取最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 获取第一个元素
    public E getFirst(){
        return get(0);
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        //检查入参合法性
        if(index < 0 || index > size){
            throw new IllegalArgumentException("传入的参数:"+index+"不合法!");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e){
        for (E n : data){
            if (n.equals(e)){
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index){
        //检查入参合法性
        if(index < 0 || index > size){
            throw new IllegalArgumentException("传入的参数:"+index+"不合法!");
        }
        //保留要删除的元素
        E res = data[index];
        //从index位置开始,复制后一个元素,直到最后一个元素为止
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
//        if (size - index + 1 >= 0){
//            System.arraycopy(data, index + 1, data, index + 1 - 1, size - index + 1);
//        }
        //维护size变量
        size --;
        //删除最后一个元素
        data[size] = null;
        //判断当前数组空间大小,如果当前数组大小是数组存在元素个数的四倍就缩容
        if (size == data.length/4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return res;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    // 从数组中删除元素e
    public void removeElement(E e){
        //先找到参数e的索引
        int i = find(e);
        if (i != -1){
            remove(i);
        }
    }

    @Override
    public String toString() {
        return "Array{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    //测试
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for(int i = 0 ; i < 4 ; i ++){
            arr.removeFirst();
            System.out.println(arr);
        }
    }
}
