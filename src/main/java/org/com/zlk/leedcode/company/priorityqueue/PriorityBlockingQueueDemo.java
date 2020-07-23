package org.com.zlk.leedcode.company.priorityqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级排序的 线程安全阻塞式队列
 * 重入锁ReentrantLock
 * 结合Condition，用于队列空情况下的阻塞,PriorityBlockingQueue可以在queue为空时阻塞take操作
 * 通过CAS手段对queue扩容  (allocationSpinLock为0，并且CAS将其置为1时,线程才能够对数组进行扩容)
 *
 * @Author zc217
 * @Date 2020/7/1
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {

        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread[] taskThreads = new Thread[5];
        for (int i = 0; i < taskThreads.length; i++) {
            // 创建5个task
            Task task = new Task(i, queue);
            // 创建5个线程
            taskThreads[i] = new Thread(task);
        }
        // 启动5个线程
        for (int i = 0; i < taskThreads.length; i++) {
            taskThreads[i].start();
        }
        for (int i = 0; i < taskThreads.length; i++) {
            try {
                // 主线程等待等待五个线程执行结束
                taskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Main Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length * 200; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }
        System.out.printf("Main Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }
}
