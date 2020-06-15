package org.com.zlk.zhouyang.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * 人到齐后，组会开会
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(6, ()-> System.out.println("开始开会！！！"));

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 到达会议");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}
