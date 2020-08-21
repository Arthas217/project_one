package org.com.zlk.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @Author zc217
 * @Date 2020/8/19
 */
public class CompltableFutureDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + "start");

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("abc");
        completableFuture.whenCompleteAsync((s,t)->{
//        completableFuture.whenComplete((s,t)->{   // 使用相同的线程执行
            if (t!=null) {
                t.printStackTrace();
            }else {
                System.out.println(Thread.currentThread() + s);
            }
        }, Executors.newSingleThreadExecutor());

        System.out.println(Thread.currentThread() + "over");
    }
}
