package org.com.zlk.datastructure.queue;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于数组实现队列(非循环）
 * https://time.geekbang.org/column/article/41330
 * @Date 2021/12/20 22:07
 */
public class ArrayQueue2jk {

    private String[] items;
    /**
     * 数组大小为n
     */
    private int n = 0;
    /**
     * head表示队头下标
     */
    private int head = 0;
    /**
     * tail表示队尾下标
     */
    private int tail = 0;

    public ArrayQueue2jk(int capacity) {
        items = new String[capacity];
        n = capacity;
    }


    /**
     * 出队
     *
     * @return
     */
    public String deQueue() {
        // 队列为空
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        ++head;
        return ret;
    }


    /**
     * 入队
     *
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        //表示队列末尾没有空间了
        if (tail == n) {
            // 队列为满
            if (head == 0) {
                return false;
            }
            // 移动数据
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            // head,tail重新赋值
            tail = tail - head;
            head = 0;
        }
        items[tail] = item;
        ++tail;
        return true;
    }

}
