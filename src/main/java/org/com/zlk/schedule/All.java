package org.com.zlk.schedule;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zc217
 * @Date 2020/11/27
 */
public class All {
    public static void main(String[] args) {
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(4);
        System.out.println("task begin:"+System.currentTimeMillis()/1000);
        respScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task1 run:"+System.currentTimeMillis()/1000);
            }
        },2,3, TimeUnit.SECONDS);
        System.out.println("main Thread gogogo");
        respScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task2 run:"+System.currentTimeMillis()/1000);
            }
        },2,3, TimeUnit.SECONDS);
        System.out.println("main Thread gogogo222");
        respScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--task3 run:"+System.currentTimeMillis()/1000);
            }
        },2,TimeUnit.SECONDS);
    }
//    写在一起就和线程池有关系（线程池线程2个和各个定时任务时间有联系）
//    task begin:1606442081
//    main Thread gogogo
//    main Thread gogogo222
//    pool-1-thread-1--task1 run:1606442088
//    pool-1-thread-2--task2 run:1606442088 // 线程池两个线程，所以同时执行
//    pool-1-thread-2--task1 run:1606442093 // AtFixedRate每5s执行任务（执行时间>period)
//    pool-1-thread-1--task3 run:1606442093 // 执行一次，需要等待其中一个任务执行完，有空的线程才执行task3
//    pool-1-thread-1--task2 run:1606442098 // WithFixedDelay每8s执行，但是线程thread-1这时在执行task3，需要多等会。
//    pool-1-thread-2--task1 run:1606442098 // AtFixedRate没5s执行任务
//    pool-1-thread-2--task1 run:1606442103 // AtFixedRate没5s执行任务
//    pool-1-thread-1--task2 run:1606442106 // WithFixedDelay每8s执行，没有其他线程在占用thread-1
//    pool-1-thread-2--task1 run:1606442108


//    写在一起就和线程池有关系（线程池线程3个和各个定时任务时间有联系）
//    task begin:1606442743
//    main Thread gogogo
//    main Thread gogogo222
//    pool-1-thread-3--task3 run:1606442750
//    pool-1-thread-1--task1 run:1606442750
//    pool-1-thread-2--task2 run:1606442750 // 线程池三个线程，所以同时执行
//    pool-1-thread-1--task1 run:1606442755 // AtFixedRate每5s执行任务
//    pool-1-thread-2--task2 run:1606442758 // WithFixedDelay每8s执行，
//    pool-1-thread-1--task1 run:1606442760
//    pool-1-thread-1--task1 run:1606442765 // AtFixedRate每5s执行任务
//    pool-1-thread-2--task2 run:1606442766 // WithFixedDelay每8s执行，
//    pool-1-thread-1--task1 run:1606442770
//    pool-1-thread-2--task2 run:1606442774 // WithFixedDelay每8s执行，
//    pool-1-thread-1--task1 run:1606442775
//    pool-1-thread-1--task1 run:1606442780



//    task begin:1606442942  线程池线程4个  执行task2时，可能会有不同线程来执行。
//    main Thread gogogo
//    main Thread gogogo222
//    pool-1-thread-2--task2 run:1606442949 // WithFixedDelay每8s执行，
//    pool-1-thread-3--task3 run:1606442949
//    pool-1-thread-1--task1 run:1606442949
//    pool-1-thread-2--task1 run:1606442954
//    pool-1-thread-1--task2 run:1606442957 // WithFixedDelay每8s执行，
//    pool-1-thread-2--task1 run:1606442959
//    pool-1-thread-2--task1 run:1606442964
//    pool-1-thread-3--task2 run:1606442965 // WithFixedDelay每8s执行，
//    pool-1-thread-2--task1 run:1606442969
//    pool-1-thread-3--task2 run:1606442973
//    pool-1-thread-2--task1 run:1606442974
//    pool-1-thread-2--task1 run:1606442979
//    pool-1-thread-4--task2 run:1606442981 // WithFixedDelay每8s执行，
//    pool-1-thread-2--task1 run:1606442984
//    pool-1-thread-2--task1 run:1606442989
//    pool-1-thread-1--task2 run:1606442989
}
