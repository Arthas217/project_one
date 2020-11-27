package org.com.zlk.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/liyantianmin/article/details/73499137
 * @Author zc217
 * @Date 2020/11/27
 */
public class Schedule {
    public static void main(String[] args) {
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:"+System.currentTimeMillis()/1000);
        respScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task run:"+System.currentTimeMillis()/1000);
            }
        },2, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"结束");
        // t1  t2= delay + 任务时间
    }
}
