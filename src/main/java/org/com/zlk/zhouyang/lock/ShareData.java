package org.com.zlk.zhouyang.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 共享资源
 * 多线程同步处理（多线程协调）
 */
public class ShareData {

    private int symbol = 1;// 1 A 2 B 3C  标志位

    // 多线程操作资源类竞争，加锁（1把锁）
    private Lock lock = new ReentrantLock();

    // 3把备用钥匙（分组唤醒）
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    /**
     * 线程AA
     */
    public void print5() {
        lock.lock();
        try {
            // 避免虚假唤醒
            while (symbol != 1) {//不是A
                condition1.await();  //A等待
            }
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbol = 2;
            condition2.signal();// 唤醒线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 线程BB
     */
    public void print10() {
        lock.lock();
        try {
            while (symbol != 2) {
                condition2.await();
            }
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbol = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 线程CC
     */
    public void print15() {
        lock.lock();
        try {
            while (symbol != 3) {
                condition3.await();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            symbol = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
