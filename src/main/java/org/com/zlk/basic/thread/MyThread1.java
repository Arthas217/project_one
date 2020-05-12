package org.com.zlk.basic.thread;

import java.util.concurrent.TimeUnit;

public class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread#1===" + Thread.currentThread() + "=====" + i);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
