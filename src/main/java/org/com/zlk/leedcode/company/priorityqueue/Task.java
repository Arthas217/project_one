package org.com.zlk.leedcode.company.priorityqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @Author zc217
 * @Date 2020/7/1
 */
public class Task implements Runnable {

    private int id;
    private PriorityBlockingQueue<Event> queue;

    public Task(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        // 线程体 将任务id和i 构建的even对象（元素）放入到queue中
        for (int i = 0; i < 200; i++) {
            Event event = new Event(id, i);
            queue.offer(event);
        }
    }

}
