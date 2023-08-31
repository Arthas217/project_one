package org.com.zlk.chxg.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 会游泳的蚂蚁
 * @description: 简易版本的线程池
 * https://bugstack.cn/md/java/interview/2020-12-09-%E9%9D%A2%E7%BB%8F%E6%89%8B%E5%86%8C%20%C2%B7%20%E7%AC%AC21%E7%AF%87%E3%80%8A%E6%89%8B%E5%86%99%E7%BA%BF%E7%A8%8B%E6%B1%A0%EF%BC%8C%E5%AF%B9%E7%85%A7%E5%AD%A6%E4%B9%A0ThreadPoolExecutor%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%AE%9E%E7%8E%B0%E5%8E%9F%E7%90%86%EF%BC%81%E3%80%8B.html
 * @date 2023/8/31 16:15
 */
public class ThreadPoolSimple implements Executor {

    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private final BlockingQueue<Runnable> workQueue;
    private final AtomicInteger ctl = new AtomicInteger(0);

    public ThreadPoolSimple(Integer corePoolSize, Integer maximumPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
    }

    @Override
    public void execute(Runnable command) {
        //只获取线程数的个数
        int c = ctl.get();
        //线程数小于corePoolSize核心线程数
        if (c < corePoolSize) {
            // 尝试创建核心线程执行任务
            if (!addWorker(command)) {
                reject();
            }
            return;
        }
        // 任务放入队列
        if (!workQueue.offer(command)) {
            //队列已满，创建非核心线程执行任务
            if (!addWorker(command)) {
                reject();
            }
        }
    }

    /**
     * 直接抛异常
     */
    private void reject() {
        throw new RuntimeException("Error！ctl.count：" + ctl.get() + " workQueue.size：" + workQueue.size());
    }

    /**
     * 把任务添加到包装了线程的worker对象中，并执行任务，增加线程个数
     * @param firstTask
     * @return
     */
    private boolean addWorker(Runnable firstTask) {
        if (ctl.get() >= maximumPoolSize) return false;
        Worker worker = new Worker(firstTask);
        worker.thread.start();
        ctl.incrementAndGet();
        return true;
    }


    /**
     * 一个worker对应一个线程
     */
    private final class Worker implements Runnable {
        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }

        /**
         * 使用复用线程执行任务，如果task为空，任务处理完，线程数减1
         */
        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask()) != null) {
                    task.run();
                    if (ctl.get() > maximumPoolSize) {
                        break;
                    }
                    task = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }

        /**
         * 从队列中取出任务
         * @return
         */
        private Runnable getTask() {
            for (; ; ) {
                try {
                    System.out.println("workQueue.size：" + workQueue.size());
                    return workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolSimple threadPoolSimple = new ThreadPoolSimple(2, 2, new ArrayBlockingQueue<Runnable>(10));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPoolSimple.execute(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务编号：" + finalI);
            });
        }
    }


}
