package org.com.zlk.zhouyang.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
public class MyCache {

    // cache
    private static volatile Map<String, Object> map = new HashMap<>();

    // 独占锁  同一时间只能读、或者写  并发性弱
    private static Lock lock = new ReentrantLock();

    // 读写锁  同一时间满足多个读  （适合缓存场景） 且可重入
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 写操作(原子操作、独占不可中断）
     */
    public static void putOperation(String key, Object value) {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入 " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成 " + key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    /**
     * 读操作
     */
    public static void getOperation(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读入 " + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读入完成 " + key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
