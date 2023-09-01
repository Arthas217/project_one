package org.com.zlk.chxg.juc.test;

import org.com.zlk.chxg.juc.SyncLock;

/**
 * @author 会游泳的蚂蚁
 * @description:
 * @date 2023/9/1 13:33
 */
public class SyncLockTest {

    public static void main(String[] args) throws InterruptedException {
        final SyncLock lock = new SyncLock();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock),String.valueOf(i)).
                    start();
        }
        Thread.sleep(100000);
    }


    static class TestLock implements Runnable {
        private SyncLock lock;
        public TestLock(SyncLock lock) throws InterruptedException {
            this.lock = lock;
        }
        @Override
        public void run() {
            lock.lock();
            try {
                //需要设置一个随机休眠时间来验证结果，线程是每200毫秒创建一个，
                // 如果每个线程都休眠相同的时间，先创建的线程肯定会先执行完的，就算不加公平锁，结果仍然是顺序输出
                int randomNumber = (int) Math.ceil(Math.random() * 1000);
                Thread.sleep(randomNumber);
                System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
