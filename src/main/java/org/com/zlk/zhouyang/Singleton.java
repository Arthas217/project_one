package org.com.zlk.zhouyang;

/**
 * 高并发下单例模式
 * 双重检测锁 + volatile
 */
public class Singleton {

    /**
     * https://www.jianshu.com/p/45885e50d1c4
     * new Singleton() 顺序：分配对象的内存空间、初始化对象、设置instance指向刚分配内存地址
     * volatile保证禁止指令重排序
     */
    private volatile Singleton instance;

    private Singleton() {
        System.out.println(Thread.currentThread() + "----------");
    }

    public Singleton getInstance() {
        if (instance == null) { // 功能性不会丢失，只是降低synchronized带来的性能开销
            synchronized (Singleton.class) { // 加锁和初始化操作
                if (instance == null) {  // 如果不加，会创建多个实例化对象
                    instance = new Singleton(); // 非原子操作
                }
            }
        }
        return instance;
    }

}