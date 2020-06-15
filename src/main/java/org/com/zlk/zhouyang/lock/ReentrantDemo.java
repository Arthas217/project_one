package org.com.zlk.zhouyang.lock;

import org.com.zlk.zhouyang.model.Phone;

/**
 * 可重入锁举例
 */
public class ReentrantDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        // 线程操纵资源类   synchronized的可重入锁方式
        new Thread(()-> phone.sendSMS(),"t1").start();
        new Thread(()-> phone.sendSMS(),"t2").start();

        // 线程操纵资源类   ReentrantLock的可重入锁方式
        new Thread(()-> phone.ReentrantGet(),"t3").start();
        new Thread(()-> phone.ReentrantGet(),"t4").start();



    }
}
