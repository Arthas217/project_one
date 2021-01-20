package org.com.zlk.datastructure.queue;

import java.lang.reflect.Array;

/**
 * 数组实现队列
 *
 * @Author zc217
 * @Date 2020/7/31
 */
public class ArrayQueue<T> {

    // 队列底层arr数组
    private T[] arr;
    // 队列容量
    private final int capacity;
    // 进队列使用的索引
    private int tail;
    // 出队列使用的索引
    private int head;
    // 队列中元素的个数
    private int len;

    public ArrayQueue(Class<T> type,int size) {
        arr = (T[]) Array.newInstance(type, size);
        capacity = size;
        tail = 0;
        head = 0;
        len = 0;
    }

    public T inQueue(T e) {
        if (!isFull(arr)) {
            arr[tail++ % capacity] = e;
            len++;
            return e;
        } else {
            System.out.print("队列已满");
            return null;
        }
    }

    public T outQueue() {
        if (!isEmpty(arr)) {
            //获取此位置值
            T curValue = arr[head % capacity];
            arr[head++ % capacity] = null;
            len--;
            return curValue;
        } else {
            System.out.println("队列无元素");
            return null;
        }
    }

    public T showHead() {
        if (len == 0) {
            System.out.print("队列为空 ");
            return null;
        }
        return arr[head % capacity];
    }


    public boolean isEmpty(T[] arr) {
        return arr.length == 0;
    }

    public boolean isFull(T[] arr) {
        return len == capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLen() {
        return len;
    }

    public void printArr() {
        for (int i = 0; i < capacity; i++) {
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }

}
