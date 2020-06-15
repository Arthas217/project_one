package org.com.zlk.zhouyang.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {

    private static AtomicReference atomicReference = new AtomicReference();

    private static void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t  in myLock! ");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(thread + "try spin spin lock .....");
        }
        System.out.println(Thread.currentThread().getName() + "\t  finish myLock! ");
    }

    private static void UnMyLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t  in UnMyLock! ");
        if (atomicReference.compareAndSet(thread, null)) {
            System.out.println(Thread.currentThread().getName() + "\t  finish UnMyLock! ");
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            myLock();
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UnMyLock();
        }, "AA").start();

        new Thread(() -> {
            myLock();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UnMyLock();
        }, "BB").start();

    }
}
