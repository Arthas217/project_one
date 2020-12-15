package org.com.zlk.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author zc217
 * @Date 2020/8/19
 */
public class CompltableFutureDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + "start");

        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        completableFuture.complete(123);

//        completableFuture.whenComplete((s, t) -> {   // 使用相同主线程执行
        completableFuture.whenCompleteAsync((s, t) -> {  //线程池线程执行任务，主线程回调继续往下执行
            if (t != null) {
                System.out.println("------------" + t);
                t.printStackTrace();
            } else {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t=" + t);
                System.out.println("s=" + s);
                System.out.println(Thread.currentThread());
            }
//        });
        }, Executors.newSingleThreadExecutor());

        System.out.println(Thread.currentThread() + "over");
    }
}
