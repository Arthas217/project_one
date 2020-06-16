package org.com.zlk.zhouyang.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 资源类
 * 多线程、高并发、高内聚低耦合口诀：
 * 1）线程 加锁操作 资源类
 * 2）判断、干活、通知
 * 3）多线程中防止虚假唤醒 while使用
 */
public class KongTiao {

    // 温度
    private int num = 0;

    //线程操作资源类必然有争抢，必须加锁
    private Lock lock = new ReentrantLock();

    // 老版本用version1  方法添加synchronized、this.wait/this.notify
    // 现在使用version2  lock/await/signalAll
    private Condition condition = lock.newCondition();

    //对资源的加操作方法
    public void incrValue() {
        lock.lock();
        // 生产
        try {
            // 判断
            while (num != 0) {
                // 等待不能生产
                condition.await();
            }
            // 干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //对资源的减操作方法
    public void decrValue() {
        lock.lock();
        // 消费
        try {
            // 判断
            while (num == 0) {
                // 等待不能消费
                condition.await();
            }
            // 干活
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
