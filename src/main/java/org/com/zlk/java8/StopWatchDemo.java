package org.com.zlk.java8;

import org.springframework.util.StopWatch;


/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/24 09:02
 */
public class StopWatchDemo {

    public static void main(String[] args) {
        try {
            method_1_stop_watch();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }


    private static void method_1_stop_watch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("任务耗时秒表工具");
        stopWatch.start("task1");
        Thread.sleep(1000);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
        stopWatch.start("task2");
        Thread.sleep(3000);
        stopWatch.stop();
        //所有任务耗时时间
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.prettyPrint());
    }
}
