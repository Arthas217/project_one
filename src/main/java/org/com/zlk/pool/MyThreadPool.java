package org.com.zlk.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 实现一个简单的线程池
 * https://blog.csdn.net/z55887/article/details/79060070
 * @Date 2021/1/25 17:02
 */
public class MyThreadPool {

    //线程池大小
    private int threadNum;
    //工作线程数目
    private int workThreadNum;
    //存放线程集合
    private List<MyThead> threads;
    //任务队列
    private BlockingQueue<Runnable> taskQueue;

    //自定义线程类
    class MyThead extends Thread {
        //初始化任务
        private Runnable task;

        public MyThead(Runnable runnable) {
            this.task = runnable;
        }

        //该线程一直启动着，不断从任务队列取出任务执行
        @Override
        public void run() {
            while (true) {
                //如果初始化任务不为空，则执行初始化任务
                if (task != null) {
                    task.run();
                    task = null;
                } else {
                    //否则去任务队列取任务并执行
                    Runnable queueTask = taskQueue.poll();
                    if (queueTask != null) {
                        queueTask.run();
                    }
                }
            }
        }
    }

    //线程池构造器
    public MyThreadPool(int cap) {
        threadNum = cap;
        threads = new ArrayList<>(cap);
        //任务队列大小为线程池线程数的四倍
        taskQueue = new ArrayBlockingQueue<>(1 * cap);
        workThreadNum = 0;
    }


    private final ReentrantLock lock = new ReentrantLock();

    // 提交任务给线程池
    public void execute(Runnable runnable) {
        lock.lock();
        try {
            //线程池未满，每加入一个任务则开启一个线程
            if (workThreadNum < threadNum) {
                MyThead newThread = new MyThead(runnable);
                newThread.start();
                threads.add(newThread);
                System.out.println("工作线程个数：" + workThreadNum);
                workThreadNum++;
            } else {
                //线程池已满，放入任务队列，等待有空闲线程时执行
                if (!taskQueue.offer(runnable)) {
                    rejectTask();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    //拒绝策略id
    private void rejectTask() {
        System.out.println("任务队列已满，无法继续添加，请扩大您的初始化线程池！");
    }

    //测试
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(5);
        //创建任务
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + "执行中");
        };
        for (int i = 0; i < 100; i++) {
            myThreadPool.execute(task);
        }
    }

}
