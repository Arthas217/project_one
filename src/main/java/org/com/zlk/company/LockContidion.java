package org.com.zlk.company;

/**
 * ReentrantLock 分组唤醒需要唤醒的线程，精确唤醒
 * 按顺序打印 A -> B -> C
 */
public class LockContidion {

    static final int NUM= 1;

    public static void main(String[] args) {

        // AA打印5次  BB打印10次 CC打印15次  依次循环10次
        ShareData shareDate = new ShareData();

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
