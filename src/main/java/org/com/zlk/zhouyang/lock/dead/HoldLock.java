package org.com.zlk.zhouyang.lock.dead;

import java.util.concurrent.TimeUnit;

/**
 * @Author zc217
 * @Date 2020/6/17
 */
public class HoldLock implements Runnable {

    private final String lockA;
    private final String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (this.lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "\t 尝试持有" + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "\t 尝试持有" + lockA);
            }
        }
    }
}
