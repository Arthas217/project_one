package org.com.zlk.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zc217
 * @Date 2020/11/27
 */
public class WithFixedDelay {
    public static void main(String[] args) {
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:" + System.currentTimeMillis() / 1000);
        respScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//5000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--task run:" + System.currentTimeMillis() / 1000);
            }
        }, 2, 3, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"结束");
    }
    // 1 任务时间< delay  t1、t2=t1+initialdelay+任务执行时间、 t3=t2+delay+任务执行时间
    // 2 任务时间> delay  t1、t2=t1+initialdelay+任务执行时间、 t3=t2+delay+任务执行时间
}
