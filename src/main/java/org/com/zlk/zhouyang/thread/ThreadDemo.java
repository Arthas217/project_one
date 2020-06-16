package org.com.zlk.zhouyang.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        createThread();
        createThreadFuture();
        futureAttention();
    }

    public static void createThread() {
        //创建线程
        Thread thread = new Thread(() -> {
            System.out.println("实现runnable接口的方式创建线程");
            System.out.println(Thread.currentThread().getName());
        }, "A");
        thread.start();
    }

    public static void createThreadFuture() throws ExecutionException, InterruptedException {
        // 模拟线程main处理方法得到结果
        int r1 = 100;

        // 模拟线程B 处理任务耗时长一些（并发、异步场景）
        // 为了尽量避免堵塞 ，通过Callable方式新创建线程, 涉及接口Runnable - RunnableFuture - FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.MICROSECONDS.sleep(1);
                System.out.println( "########Callable方式创建线程############"+ Thread.currentThread().getName());
                return 1;
            }
        });
        new Thread(futureTask, "B").start();

        System.out.println("-----main的计算---------");

        while (!futureTask.isDone()) {
            System.out.println("在等futureTask创建的线程B");
        }
        // futureTask最好是放在最后，获取线程B结果，如果没有计算完成会导致阻塞，直到计算完成。
        System.out.println("最后的计算结果result :" + r1 + futureTask.get());
    }



    public static void futureAttention() {

        FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
            TimeUnit.MICROSECONDS.sleep(1);
            System.out.println( "########Callable方式创建线程############"+ Thread.currentThread().getName());
            return 1;
        });

        //solve
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            TimeUnit.MICROSECONDS.sleep(1);
            System.out.println( "########Callable方式创建线程############"+ Thread.currentThread().getName());
            return 1;
        });

        // 多个线程执行一个futureTask(两个线程干了同一件事情),计算结果算一次
        new Thread(futureTask1, "AAAAAAAAAAA").start();
        new Thread(futureTask2, "BBBBBBBBBBB").start();

    }
}
