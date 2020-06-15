package org.com.zlk.basic.thread.createthread;

import java.util.concurrent.FutureTask;

public class CreateThread {

    public static void main(String[] args) throws InterruptedException {
        // 启动三个线程
        long startTime = System.currentTimeMillis();
        // 通过主线程启动自己的线程
        MyThread1 thread1 = new MyThread1();
        thread1.start();

        Thread thread2 = new Thread(new MyThread2());
        thread2.start();

        MyThread3 thread3 = new MyThread3();
        FutureTask<Integer> futureTask = new FutureTask<>(thread3);
        Thread thread = new Thread(futureTask);
        // 可以设置守护线程
        thread.setDaemon(true);
        thread.start();
        System.out.println("thread###===" + thread + "  守护线程？" + thread.isDaemon());


        Thread.sleep(2000);// 防止主线程退出 得不到最后time结果
        System.out.println("main方法是守护进程： " + Thread.currentThread().isDaemon());
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("主线程结束： " + Thread.currentThread() + "花费时间 " + time);
    }
}
