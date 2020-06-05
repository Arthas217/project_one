package org.com.zlk.zhouyang.guanjianzi;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {

        //volatile可见性
        seeOkByVolatile();

        // volatile原子性(数据操作的一致性)吗？ 不支持
        atomicByVolatile();
    }

    private static void atomicByVolatile() throws InterruptedException {
        ShareData data = new ShareData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addPP();
                }
            }, String.valueOf(i)).start();
        }
        // 这里注意 一般默认后台是2个线程 （主线程、GC线程）
        while (Thread.activeCount() > 2) {
            // 该线程的礼让，让其他线程执行
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "------------finally num value：" + data.num);
    }

    private static void seeOkByVolatile() {
        ShareData data = new ShareData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "------------start----------");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // AA线程3s后修改num数据值
            data.addNum();
            System.out.println(Thread.currentThread().getName() + "----------add end----------");
        }, "AA").start();

        // 无可见性的话  主线程感知不到num变化 主线程一直在等
        while (data.num == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "---------- session over------------");
        System.out.println(Thread.currentThread().getName() + "---------- num value:------------" + data.num);
    }
}

