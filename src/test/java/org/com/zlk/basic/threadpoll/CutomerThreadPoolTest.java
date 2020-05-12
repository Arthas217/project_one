package org.com.zlk.basic.threadpoll;

import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;

public class CutomerThreadPoolTest {

    @Test
    public void getThreadPoolTest() {
        ThreadPoolExecutor threadPool = CutomerThreadPool.getPoolInstance().getThreadPool();
        threadPool.execute(() -> {
            System.out.println("自定义单例线程池中的普通线程池");
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(300 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(Thread.currentThread().getName() + " " + threadPool.getActiveCount());
        System.out.println(Thread.currentThread().getName() + " " + threadPool.getCorePoolSize());
    }

    @Test
    public void getScheduledThreadPoolTest() {
    }
}