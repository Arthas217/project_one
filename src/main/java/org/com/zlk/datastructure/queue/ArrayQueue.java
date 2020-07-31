package org.com.zlk.datastructure.queue;

/**
 * @Author zc217
 * @Date 2020/7/31
 */
public class ArrayQueue {

    private int[] arr;
    private int capacity;
    // 进队列使用的索引
    private int tail;
    // 出队列使用的索引
    private int head;
    // 队列中元素的个数
    private int len;

    public ArrayQueue(int size) {
        arr = new int[size];
        capacity = arr.length;
        tail = 0;
        head = 0;
        len = 0;
    }

    public int inQueue(int e) {
        if (!isFull(arr)) {
            arr[tail++ % capacity] = e;
            len++;
            return e;
        } else {
            System.out.print("队列已满");
            return -1;
        }
    }

    public int outQueue() {
        if (!isEmpty(arr)) {
            int outElement = arr[head % capacity];
            arr[head++ % capacity] = 0;
            len--;
            return outElement;
        } else {
            System.out.println("队列无元素");
            return -1;
        }
    }

    public int showHead() {
        if (len == 0) {
            System.out.print("队列为空 ");
            return -1;
        }
        return arr[head % capacity];
    }


    public boolean isEmpty(int[] arr) {
        return arr.length == 0;
    }

    public boolean isFull(int[] arr) {
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
