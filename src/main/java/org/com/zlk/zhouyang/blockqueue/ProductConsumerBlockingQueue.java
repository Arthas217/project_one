package org.com.zlk.zhouyang.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者/消费者模式  (使用阻塞队列）
 * 版本3 没有使用synchronized lock
 * 需求：开关true  开启生产-消费 ；开关false 叫停
 */
public class ProductConsumerBlockingQueue {

    public static void main(String[] args) {
        MyResource resource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
                resource.myProduct();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "product").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
                resource.myConsume();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();


        try {
            // 开关打开5s秒钟时间
            TimeUnit.SECONDS.sleep(5);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(Thread.currentThread().getName() + "大老版main线程叫停，活动结束");
            resource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}
