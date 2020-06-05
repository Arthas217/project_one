package org.com.zlk.zhouyang.guanjianzi;

import java.util.concurrent.TimeUnit;

/**
 * volatile可见性
 */
public class VolatileDemo {

    public static void main(String[] args) {

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

class ShareData {
    //    int num = 0;
    volatile int num = 0;

    public void addNum() {
        this.num = 60;
    }
}
