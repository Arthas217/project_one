package org.com.zlk.zhouyang.lock;

/**
 * ReentrantLock、 Condition的await/signal
 * 优势 分组唤醒需要唤醒的线程、精确唤醒
 * 按顺序打印 A -> B -> C
 * 线程同步处理
 */
public class LockContidion {

    public static final int NUM= 1;

    public static void main(String[] args) {

        ShareData shareDate = new ShareData();

        //线程AA打印5次 BB打印10次 CC打印15次
        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                shareDate.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                shareDate.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < NUM; i++) {
                shareDate.print15();
            }
        }, "CC").start();


    }
}
