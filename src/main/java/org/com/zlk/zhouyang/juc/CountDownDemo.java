package org.com.zlk.zhouyang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 火箭发射倒计时
 */
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {
//        failCase();
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println("-------------");
        correctCase();
        System.out.println("-------------");
        //枚举
        System.out.println(NameEnum.NAME1);
        System.out.println(NameEnum.NAME1.getCode());
        System.out.println(NameEnum.NAME1.getMessage());
    }

    public static void failCase() {
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 从图书馆离开");
            }, String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t 管理员闭馆");
    }

    public static void correctCase() {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 从图书馆离开");
                // 某线程运行到某个点上之后，只是给某个数值-1而已，该线程继续运行
                countDownLatch.countDown();
            }, NameEnum.getName(i).getMessage()).start();   //这里使用枚举的方式来解决映射值（值很多）
        }
        try {

            countDownLatch.await();// 保证在上面的6个线程都执行完之后，主线程在执行的动作
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 管理员闭馆");
    }
}
