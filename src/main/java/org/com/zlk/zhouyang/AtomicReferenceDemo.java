package org.com.zlk.zhouyang;

import org.com.zlk.zhouyang.model.User;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA- 原子引用更新
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        // 原子引用使用方法
//        useAtomicReference();
        // 模拟ABA问题
//        ABA();
        // 解决ABA问题（CC在2s后执行修改，在修回操作，D等3s后在修改）
        solveABA();
    }

    private static void useAtomicReference() {
        User zhang = new User("ZhangSan", 1);
        User li = new User("LiSi", 2);
        AtomicReference<User> atomicReference = new AtomicReference(zhang);
        System.out.println(atomicReference.compareAndSet(zhang, li) + "   " + atomicReference.get().toString());
    }

    private static AtomicReference<Integer> atomicref = new AtomicReference<>(100);

    private static void ABA() {
        new Thread(() -> {
            atomicref.compareAndSet(100, 102);
            atomicref.compareAndSet(102, 100);
        }, "AA").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" data：" + atomicref.get() + " " + atomicref.compareAndSet(100, 2020));
        }, "BB").start();
    }


    private static AtomicStampedReference<Integer> atomicstampref = new AtomicStampedReference<>(100, 1);

    private static void solveABA() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + atomicstampref.getStamp());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("---cc---" + atomicstampref.compareAndSet(100, 101, atomicstampref.getStamp(), atomicstampref.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "\t 第二次版本号" + atomicstampref.getStamp() + "值 " + atomicstampref.getReference());

            System.out.println("---cc---" + atomicstampref.compareAndSet(101, 100, atomicstampref.getStamp(), atomicstampref.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "\t 第三次版本号" + atomicstampref.getStamp() + "值 " + atomicstampref.getReference());
        }, "CC").start();

        new Thread(() -> {
            int version = atomicstampref.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t 第一次版本号" + version);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicstampref.compareAndSet(100, 2020, version, version + 1);
            System.out.println(Thread.currentThread().getName() + "\t 修改成功？" + result);
        }, "DD").start();
    }

}
