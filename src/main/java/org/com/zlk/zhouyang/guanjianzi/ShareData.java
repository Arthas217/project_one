package org.com.zlk.zhouyang.guanjianzi;

import java.util.concurrent.atomic.AtomicInteger;

public class ShareData {

    //    int num = 0;
    volatile int num = 0;

    public void addNum() {
        this.num = 60;
    }

    public void addPP() {
        // 多线程环境下是不安全的，线程间会有竞争调度问题。
        // 读取--计算--刷回
        num++;
    }

    public synchronized void addPP1() {
        num++;
    }


    AtomicInteger atomicInteger =new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }


}
