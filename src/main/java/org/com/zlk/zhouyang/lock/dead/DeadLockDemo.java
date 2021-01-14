package org.com.zlk.zhouyang.lock.dead;

/**
 * 动态锁顺序（2个账户转账）
 * 死锁：两个或两个以上线程执行过程中，因争夺资源造成一种相互等待的现象，若无外力干涉，他们都将无法推进下去
 * @Author zc217
 * @Date 2020/6/17
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        HoldLock holdLock1 = new HoldLock("A","B");
        new Thread(holdLock1).start();
        HoldLock holdLock2 = new HoldLock("B","A");
        new Thread(holdLock2).start();
    }

}
