package org.com.zlk.datastructure.queue;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于数组实现的循环队列
 * @Date 2021/12/21 20:50
 */
public class CircularQueue {

    private String[] items;
    private int n;

    private int head =0;
    private int tail =0;

    public CircularQueue(int cap){
        items = new String[cap];
        this.n = cap;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item) {
        if ((tail + 1) % n == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String res = items[head];
        head = (head + 1) % n;
        return res;
    }
}
