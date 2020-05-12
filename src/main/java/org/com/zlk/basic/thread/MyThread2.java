package org.com.zlk.basic.thread;

import java.util.concurrent.TimeUnit;

public class MyThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread#2===" + Thread.currentThread() + "=====" + i);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
