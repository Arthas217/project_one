package org.com.zlk.basic.thread;

import java.util.concurrent.Callable;

public class MyThread3 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("thread#3===" + Thread.currentThread() + "=====" + i);
            sum += i;
        }
        return sum;
    }
}
