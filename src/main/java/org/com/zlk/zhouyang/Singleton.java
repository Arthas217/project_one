package org.com.zlk.zhouyang;

/**
 * 高并发下单例模式
 * 双重检测锁 + volatile
 */
public class Singleton {

    /**
     * new Singleton()指令重排序存在
     * volatile保证禁止指令重排序
     */
    private static volatile Singleton instance;

    private Singleton() {
        System.out.println(Thread.currentThread()+ "----------");
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                getInstance();
            }, String.valueOf(i)).start();
        }
    }
}