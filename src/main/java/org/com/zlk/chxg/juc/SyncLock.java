package org.com.zlk.chxg.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author 会游泳的蚂蚁
 * @description: 简单实现同步锁(ReentrantLock 简版)----对标AQS
 * @date 2023/9/1 13:22
 */
public class SyncLock {

    private final Sync sync;

    public SyncLock() {
        sync = new Sync();
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }


    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 主要是使用 AQS 提供的 CAS 方法。以预期值为 0，写入更新值 1，写入成功则获取锁成功。其实这个过程就是对 state 使用 unsafe 本地方法，传递偏移量 stateOffset 等参数，进行值交换操作。
         * unsafe.compareAndSwapInt(this, stateOffset, expect, update)
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        /**
         * 该线程是否正在独占资源，只有用到 Condition 才需要去实现
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

}
