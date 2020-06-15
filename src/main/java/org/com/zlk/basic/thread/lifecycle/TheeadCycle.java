package org.com.zlk.basic.thread.lifecycle;

public class TheeadCycle {

    public static void main(String[] args)  {

        //新建状态(NEW) :     new 关键字创建了一个线程之后,仅由 JVM 为其分配 内存，并初始化其成员变量的值
        //就绪状态(RUNNABLE): 调用了 start()方法之后,Java 虚拟机会为其创建方法调用栈和 程序计数器，等待调度运行
        //运行状态(RUNNING):  线程获得了CPU ,执行run()方法的线程执行体

        //阻塞状态(BLOCKED)   放弃了cpu使用权
           // 1)等待阻塞(o.wait->等待对列)   JVM 会把该线程放入等待队列(waitting queue) 中。
           // 2)同步阻塞(lock->锁池)  获取对象的同步锁时，该同步锁被别的线程占用，则JVM 会把该线程放入锁池(lock pool)中。
           // 3)其他阻塞(sleep/join/I/O请求)  JVM会把该线程置为阻塞状态

        //线程死亡(DEAD)  1）正常结束  2）异常结束-线程抛出一个未捕获的 Exception 或 Error。3） 调用 stop 容器造成死锁


        Thread aaa = new Thread(() -> {
            System.out.println(Thread.currentThread() + " aaa " + Thread.currentThread().getState());
        }, "AAA");

        Thread.State state = aaa.getState();
        System.out.println(state);

        aaa.start();

        System.out.println(Thread.currentThread() + " main " + Thread.currentThread().getState());


    }
}
