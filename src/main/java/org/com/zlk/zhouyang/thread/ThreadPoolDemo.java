package org.com.zlk.zhouyang.thread;

import java.util.concurrent.*;

/**
 * 都是API级别的线程池
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        // cpu核数
//        System.out.println(Runtime.getRuntime().availableProcessors());

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
     *  10个用户来办理业务，每个用户对应一个请求任务
     */
    private static void handleTask(ExecutorService threadPool) {
        try {
            for (int i = 0; i < 10; i++) {
                // execute参数是runnable接口
                // execute参数是runnable接口
                // submit参数可以是runnble、callable等 且都是有返回值
                threadPool.execute(() ->
                        System.out.println(Thread.currentThread().getName() + "\t 处理业务员"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }


    /**
     * ExecutorService方式 自定义线程池
     */
    private static void customThreadPool() {
        ExecutorService customThreadPool = new ThreadPoolExecutor(
                1,
                2,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
                new ThreadPoolExecutor.CallerRunsPolicy());
//                new ThreadPoolExecutor.DiscardOldestPolicy());
//                new ThreadPoolExecutor.DiscardPolicy());
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
