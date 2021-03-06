package org.com.zlk.zhouyang.guanjianzi;

import java.util.concurrent.TimeUnit;

/**
 * 1.验证volatile可见性
 *   1.1 ShareData类中 int num =0 无可见性
 *   1.2 添加volatile可见性
 *   1.3 举例seeOkByVolatile方法
 *
 * 2.不保证原子性
 *   2.1 原子性：线程在做某个业务处理时，中间不可以加塞、不可分割  --保证数据一致性
 *   2.2 举例：atomicByVolatile方法
 *   2.3 解决方法 ：方法前加synchronized（太重） 、 AtomicInteger相关
 *
 *
 * 3.禁止指令重排序
 *   2.1 举例 forbidCommandReSort方法
 *
 */
public class VolatileDemo {

    volatile int a = 0;
    volatile boolean flag = false;

    /**
     * 多线程环境中线程交替执行，编译器优化重排的存在
     * 线程中使用的变量a和flag不能保证一致性。
     */
    public void method1() {
        // 下面两个语句没有依赖性,顺序可以调整
        // 禁止排序  将a和flag都添加volatile关键字
        a = 1;        //11
        flag = true;  //22
    }

    public void method2() {
        if (flag) {
            a = a + 5;
            if (a != 6) {
                System.out.println("------" + a);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //volatile可见性
//        seeOkByVolatile();

        // volatile原子性(数据操作的一致性)吗？ 不支持
//        atomicByVolatile();

        //举例 volatile禁止指令重排序
        forbidCommandReSort();
    }

    private static void forbidCommandReSort() {
        VolatileDemo demo = new VolatileDemo();
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1; j++) {
                    demo.method1();
                    demo.method2();
                }
            }, String.valueOf(i)).start();
        }
    }

    private static void atomicByVolatile() throws InterruptedException {
        ShareData data = new ShareData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    // 无原子性
                    data.addPP();
                    // 保证原子性
                    data.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        // 这里注意 一般默认后台是2个线程 （主线程、GC线程）
        while (Thread.activeCount() > 2) {
            // 该线程的礼让，让其他线程执行
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "----int --------finally num value：" + data.num);
        System.out.println(Thread.currentThread().getName() + "----atomic--------finally num value：" + data.atomicInteger);
    }

    private static void seeOkByVolatile() {
        ShareData data = new ShareData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "------------start----------");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // AA线程3s后修改num数据值
            data.addNum();
            System.out.println(Thread.currentThread().getName() + "----------add end----------");
        }, "AA").start();

        // 无可见性的话  主线程感知不到num变化 主线程一直在等
        while (data.num == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "---------- session over------------");
        System.out.println(Thread.currentThread().getName() + "---------- num value:------------" + data.num);
    }
}

