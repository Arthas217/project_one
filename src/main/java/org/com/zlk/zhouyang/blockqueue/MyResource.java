package org.com.zlk.zhouyang.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 资源类
 * 功能：生产和消费匹配配对使用
 * 通过构造 传参为接口 落地阻塞队列的实现
 */
public class MyResource {

    // 开关：默认开启生产-消费动作的处理，线程间需要可见性
    private volatile boolean FLAG = true;
    // 需要添加的数据
    AtomicInteger atomicInteger = new AtomicInteger();
    // 阻塞队列
    BlockingQueue<String> blockingQueue;
    // 参数传接口
    public MyResource(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.getClass().getName() + "------------------");
        this.blockingQueue = blockingQueue;
    }

    /**
     * 生产-队列
     */
    public void myProduct() throws InterruptedException {
        boolean result;
        String key;
        while (FLAG) {
            key = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(key, 1, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + key + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + key + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停 FLAG =false 停止生产");
    }

    /**
     * 消费-队列
     */
    public void myConsume() throws InterruptedException {
        String result;
        while (FLAG) {
            // 生产消费匹配1-1
            // timeout时间太小的话，不能保证生产的data消费掉
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                // 无消费时，保证停止消费
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t  2s钟没有取到消息值，停止消费");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    /**
     * 开关关闭
     */
    public void stop() {
        this.FLAG = false;
    }
}
