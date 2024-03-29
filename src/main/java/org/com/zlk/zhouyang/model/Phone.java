package org.com.zlk.zhouyang.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 */
public class Phone {

    /**
     * 发短信
     */
    public synchronized void sendSMS() {

        System.out.println(Thread.currentThread().getName() + "threadid " + Thread.currentThread().getId() + "\t 发短信");
        // 同一线程在外层方法获得锁后，内层递归函数获取该锁的代码
        // 线程可以进入任何一个已经拥有的锁所同步着的代码块
        sendEmail();
    }

    /**
     * 发邮件
     */
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "threadid " + Thread.currentThread().getId() + "\t ###发邮件");
    }



    Lock lock = new ReentrantLock();

    public void reentrantGet() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "threadid " + Thread.currentThread().getId() + "\t ###get()");
            reentrantSet();
        } finally {
            lock.unlock();
        }
    }

    public void reentrantSet(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "threadid " + Thread.currentThread().getId() + "\t ###set()");
        } finally {
            lock.unlock();
        }
    }
}
