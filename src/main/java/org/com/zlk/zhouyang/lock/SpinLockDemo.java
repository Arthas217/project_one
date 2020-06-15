package org.com.zlk.zhouyang.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLockDemo {

    private static AtomicReference atomicReference = new AtomicReference();

    private static void mySpinLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t  in myLock! ");
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(thread + "try spin spin lock .....");
        }
        System.out.println(Thread.currentThread().getName() + "\t  finish myLock! ");
    }

    private static void UnSpinLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t  in UnMyLock! ");
        if (atomicReference.compareAndSet(thread, null)) {
            System.out.println(Thread.currentThread().getName() + "\t  finish UnMyLock! ");
        }
    }

    public static void main(String[] args) {

        new Thread(() -> {
            mySpinLock();
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UnSpinLock();
        }, "AA").start();

        new Thread(() -> {
            mySpinLock();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            UnSpinLock();
        }, "BB").start();

    }
}
