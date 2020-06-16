package org.com.zlk.zhouyang.lock;

/**
 * ReentrantLock、 Condition的await/signal
 * 相比Synchronized优势 分组唤醒需要唤醒的线程、精确唤醒
 * 按顺序打印 A -> B -> C
 * 线程同步处理
 */
public class LockContidion {

    // 顺序打印次数
    public static final int COUNT = 1;

    public static void main(String[] args) {

        ShareData shareDate = new ShareData();

        //线程A打印5次 BB打印10次 CC打印15次
        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                shareDate.ThreadOnePrint();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                shareDate.ThreadTwoPrint();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < COUNT; i++) {
                shareDate.ThreadThreePrint();
            }
        }, "C").start();
    }
}
