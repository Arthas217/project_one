package org.com.zlk.zhouyang.thread;

import java.util.concurrent.*;

/**
 * 都是API级别的线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        // 获取使用java多线程的方式，线程池ThreadPoolExecutor类

//        fixThreadPool();
//        singleThreadPool();
//        cacheThreadPool();
        customThreadPool();
//        scheduledThreadPool();
    }


    /**
     * 执行长期任务，性能好一些
     */
    private static void fixThreadPool() {
        // 1池（银行）  5个线程（办理窗口）
        ExecutorService threadPool = Executors.newFixedThreadPool(5); //1池5线程(固定）
        handleTask(threadPool);
    }

    /**
     * 一个任务一个任务执行的场景
     */
    private static void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();//1池1线程
        handleTask(threadPool);

    }

    /**
     * 适合执行短期、异步的、负载轻的任务
     */
    private static void cacheThreadPool() {
        // 可以扩展的线程数
        ExecutorService threadPool = Executors.newCachedThreadPool();//1池N线程
        handleTask(threadPool);
    }

    /**
     * 10个用户来办理业务，每个用户对应一个请求任务
     */
    private static void handleTask(ExecutorService threadPool) {
        try {
            for (int i = 1; i <= 10; i++) {
                // execute参数是runnable接口
                // execute参数是runnable接口
                // submit参数可以是runnble、callable等 且都是有返回值
                int temp = i;
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName() +
                        "号窗口" + "\t 处理业务员" + "服务顾客" + temp));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


    /**
     * ExecutorService方式 自定义线程池
     * 以下我举例的各种抛弃策略出现的现象是不有一定出现的，根据实际情况会有稍微的不同
     */
    private static void customThreadPool() {
        // cpu核数
        System.out.println("cpu 核数：" + Runtime.getRuntime().availableProcessors());
        ExecutorService customThreadPool = new ThreadPoolExecutor(
                2,
                5,
                100L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());  // 9号顾客办理不了业务，程序报异常 RejectedExecutionException，直接退出
//                new ThreadPoolExecutor.CallerRunsPolicy()); // 9号顾客办理业务，线程池处理不过来，将请求返回给调用者main线程了
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 10号顾客办理业务 ，抛弃之前任务，即前面的某号顾客没有得到业务办理情况
//                new ThreadPoolExecutor.DiscardPolicy()); // 10号办理业务，抛弃策略，即最近小于10号的9号顾客没有得到业务办理的情况
        handleTask(customThreadPool);
    }

    /**
     * 定期执行的线程池
     */
    private static void scheduledThreadPool() {
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        handleTask(scheduledThreadPool);
    }
}
