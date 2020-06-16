package org.com.zlk.zhouyang.blockqueue;

/**
 * 针对初始值为0的一个变量，两个线程交替执行加一、减一操作，来5轮
 * 传统生产者/消费者模式
 * 使用Lock/await/signal
 */
public class ProductConsumerTraditional {

    public static void main(String[] args) {
        KongTiao kongTiao = new KongTiao();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                kongTiao.incrValue();
            }
        }, "AA-Product").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                kongTiao.incrValue();
            }
        }, "CC-Product").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                kongTiao.decrValue();
            }
        }, "BB--Consumer").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                kongTiao.decrValue();
            }
        }, "DD--Consumer").start();
    }
}
