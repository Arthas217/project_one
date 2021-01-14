package org.com.zlk.close;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 在jvm中增加一个关闭的钩子，当jvm关闭的时会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子
 * 当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
 * @Date 2021/1/14 13:53
 */
public class ShutdownHook {
    public static void main(String[] args) {
        // 定义线程1
        Thread thread1 = new Thread(() -> System.out.println("thread1..."));
        // 定义线程2
        Thread thread2 = new Thread(() -> System.out.println("thread2..."));
        // 定义关闭线程
        Thread shutdownThread = new Thread(() -> System.out.println("shutdownThread..."));

        // jvm关闭的时候先执行该线程钩子
        Runtime.getRuntime().addShutdownHook(shutdownThread);

        thread1.start();
        thread2.start();


    }
}
