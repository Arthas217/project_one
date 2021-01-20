package org.com.zlk.zhouyang.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://www.jianshu.com/p/4a26518f0128
 * https://blog.csdn.net/u011936381/article/details/17511051
 * @Date 2021/1/20 19:59
 */
public class PrintProblem {
    public static void main(String[] args) throws InterruptedException {
        //有三个线程T1,T2,T3,如何保证顺序执行？
        test1();
        test2();

        // 主线程等待子线程执行完毕再执行
        test00();
        test11();
        test22();
        test33();
        test44();
    }

    //join
    private static void test1() {
        Thread t1 = new Thread(() -> System.out.println("t1"));
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });

        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3");
        });
        //启动顺序不影响
        t3.start();
        t1.start();
        t2.start();
    }

    //线程池保证顺序执行
    private static void test2() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> System.out.println("t1"));
        executor.submit(() -> System.out.println("t2"));
        executor.submit(() -> System.out.println("t3"));
        executor.shutdown();
    }

    //latch
    private static void test00() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("thread " + Thread.currentThread().getName() + " start to  run");
                    Thread.sleep(2000);
                    System.out.println("thread " + Thread.currentThread().getName() + " done");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }).start();
        }
        System.out.println("main start to run.");
        latch.await();
        System.out.println("main done");
    }


    private static Thread getThread() {
        return new Thread(() -> {
            try {
                System.out.println("thread " + Thread.currentThread().getName() + " start to  run");
                Thread.sleep(2000);
                System.out.println("thread " + Thread.currentThread().getName() + " done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void test33() {
        Thread t1 = getThread();
        System.out.println("main start to run.");
        t1.start();
        while (Thread.activeCount() > 2) {
            //方法作用是：暂停当前正在执行的线程对象，并执行其他线程。
            Thread.yield();
        }
        System.out.println("main done");
    }


    private static void test22() {
        Thread t1 = getThread();
        System.out.println("main start to run.");
        t1.start();
        while (t1.isAlive()) {
        }
        System.out.println("main done");
    }


    private static void test11() throws InterruptedException {
        Thread t1 = getThread();
        System.out.println("main start to run.");
        t1.start();
        t1.join();
        System.out.println("main done");
    }

    private static void test44() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("thread " + Thread.currentThread().getName() + " start to  run");
                Thread.sleep(2000);
                System.out.println("thread " + Thread.currentThread().getName() + " done");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });
        System.out.println("main start to run.");
        t1.start();
        latch.await();
        System.out.println("main done");
    }


}
