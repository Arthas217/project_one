package org.com.zlk.basic.thread;


public class MyThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread#2===" + Thread.currentThread() + "=====" + i);
        }
    }
}
