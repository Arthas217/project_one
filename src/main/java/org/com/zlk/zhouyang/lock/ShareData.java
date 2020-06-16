package org.com.zlk.zhouyang.lock;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Handler;

/**
 * 共享资源
 * 多线程同步处理（多线程协调）
 */
public class ShareData {

    // 每个线程打印次数
    private static final int PRINT_COUNT = 2;

    private int symbol = 1; // 1 A线程 2 B线程 3 C线程  标志位

    // 多线程操作资源类竞争，加锁（1把锁）
    private Lock lock = new ReentrantLock();

    // 分组唤醒(3把备用钥匙)
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    /**
     * 打印
     */
    public void ThreadOnePrint() {
        lock.lock();
        try {
            // 避免虚假唤醒
            while (symbol != 1) {
                // 不是A线程 && A线程等待
                condition1.await();
            }
            // A线程干活
            for (int i = 0; i < PRINT_COUNT; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + i);
            }
            // 通知B线程，唤醒线程B
            symbol = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void ThreadTwoPrint() {
        lock.lock();
        try {
            while (symbol != 2) {
                condition2.await();
            }
            for (int i = 0; i < PRINT_COUNT; i++) {
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

    public void ThreadThreePrint() {
        lock.lock();
        try {
            while (symbol != 3) {
                condition3.await();
            }
            for (int i = 0; i < PRINT_COUNT; i++) {
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
