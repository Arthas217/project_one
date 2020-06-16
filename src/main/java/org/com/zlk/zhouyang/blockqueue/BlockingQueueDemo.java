package org.com.zlk.zhouyang.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列接口及实现类、核心方法
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        // 实现类
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 核心方法
//        addremoveException(blockingQueue);
//        offerpollValue(blockingQueue);
//        puttakeBlock(blockingQueue);
//        offerpollBlockTime(blockingQueue);

        // 同步队列
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        synchronousQueue(synchronousQueue);


    }

    /**
     * 同步队列、不存储元素，消费后才能再生产，不生产就不消费
     */
    public static void synchronousQueue(BlockingQueue<String> synchronousQueue) {
        // 线程AAA生产
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+ "\t put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+ "\t put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+ "\t put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AAA").start();

        // 线程BBB消费
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+ "\t take 1");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+ "\t take 2");
                synchronousQueue.take();
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+ "\t take 3");
                synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BBB").start();
    }

    /**
     * 超时阻塞
     */
    public static void offerpollBlockTime(BlockingQueue<String> blockingQueue) {
        try {
            System.out.println(blockingQueue.offer("1", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("2", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("3", 2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("3", 2, TimeUnit.SECONDS));

            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 队列满，空时线程一直阻塞   消息积压或者是无消息消费
     */
    public static void puttakeBlock(BlockingQueue<String> blockingQueue) {
        try {
            blockingQueue.put("1");
            blockingQueue.put("2");
            blockingQueue.put("3");
            System.out.println("-------------");;
//            blockingQueue.put("x");

            blockingQueue.take();
            blockingQueue.take();
            blockingQueue.take();
//            blockingQueue.take();
            System.out.println("+++++++++++++");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回true /false
     */
    public static void offerpollValue(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        System.out.println(blockingQueue.offer("x"));

        //队列头
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    /**
     * 如果队列满，队列空，再操作后会产生异常
     */
    public static void addremoveException(BlockingQueue<String> blockingQueue) {
        // 抛出异常
        System.out.println(blockingQueue.add("1"));
        System.out.println(blockingQueue.add("2"));
        System.out.println(blockingQueue.add("3"));
        // java.lang.IllegalStateException: Queue full
//        System.out.println(blockingQueue.add("x"));
        // 获取队列头
        System.out.println(blockingQueue.element());

        // 移出队列头
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //Exception in thread "main" java.util.NoSuchElementException
//        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.element());


    }
}
