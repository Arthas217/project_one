package org.com.zlk.zhouyang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS - Unsafe -CAS底层原理
 */
public class CASDemo {

    public static void main(String[] args) {

        // AtomicInteger类中 体现了可见性
        // AtomicInteger类中compareAndSet方法使用到了unsafe类
        AtomicInteger atomicInteger = new AtomicInteger(5); //主内存中数值为5
        // main dosometing ...
        System.out.println(atomicInteger.compareAndSet(5, 2020) + " current data " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(2020, 2021) + " current data " + atomicInteger.get());


        // 自旋锁、unsafe ---是CAS（ 原子性）的核心类
        atomicInteger.getAndIncrement();
    }
}
