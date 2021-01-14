package org.com.zlk.zhouyang.lock.dead.yyy;

import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 锁顺序 死锁
 * @Date 2021/1/13 16:34
 */
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void lr() {
        synchronized (left) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (right) {
                System.out.println("---------lr---------");
            }
        }
    }

    public void rl() {
        synchronized (right) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (left) {
                System.out.println("---------rl---------");
            }
        }
    }

    int leftHash = System.identityHashCode(left);
    int rightHash = System.identityHashCode(right);
    // 额外的锁、避免两个对象hash值相等的情况(即使很少)
    private final Object tieLock = new Object();

    /**
     * 解决死锁---固定加锁的顺序(针对锁顺序死锁)
     */
    public void correctLR() {
        if (leftHash < rightHash) {
            System.out.println(Thread.currentThread().getName() + "|" + leftHash + "|" + rightHash);
            synchronized (left) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right) {
                    System.out.println("---------lr---------");
                }
            }
        } else if (leftHash > rightHash) {
            System.out.println(Thread.currentThread().getName() + "|" + leftHash + "|" + rightHash);
            synchronized (right) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (left) {
                    System.out.println("---------rl---------");
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (left) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (right) {
                        System.out.println("------------------");
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        LeftRightDeadlock deadlock = new LeftRightDeadlock();
        new Thread(() -> {
//            deadlock.lr();
            deadlock.correctLR();
        }, "AAA").start();
        new Thread(() -> {
//            deadlock.rl();
            deadlock.correctLR();
        }, "BBB").start();
    }

}
