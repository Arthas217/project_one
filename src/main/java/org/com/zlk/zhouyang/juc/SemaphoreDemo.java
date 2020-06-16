package org.com.zlk.zhouyang.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量（可以伸缩）
 * 主要目的：多个共享资源的互斥使用、并发线程数的控制
 * 多个线程抢多个共享资源  例如抢停车位，小米秒杀抢手机   涉及到协调互斥
 * 可以退化成synchronized （初始化1）
 */
public class SemaphoreDemo {

    // 模拟停车位
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        // 模拟汽车
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 入车位");
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
