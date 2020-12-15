package org.com.zlk.zhouyang.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 人到齐后，组会开会
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> System.out.println("开始开会！！！"));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 到达会议");
                try {
                    // CyclicBarrier的某个线程运行到某个点上之后，该线程即停止运行，直到所有的线程都到达了这个点，所有线程才重新运行；
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "发言----");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
