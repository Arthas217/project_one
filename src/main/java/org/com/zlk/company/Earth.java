package org.com.zlk.company;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 多线程场景下（线程安全），有一个盒子（盒子容量限制），往盒子放球，每个球有一个数值；
 * 要求从盒子里取球的时候，每次取最大值的那个球，如果放入球满了，线程阻塞。
 * @Author zc217
 * @Date 2020/7/1
 */
public class Earth {

    private static final int N = 3;

    public static void main(String[] args) throws InterruptedException {

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(N, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });


        for (int i = 0; i < N; i++) {
            new Thread(() -> {
                queue.offer((int) (100 * Math.random()));
            }, String.valueOf(i)).start();
        }

        TimeUnit.SECONDS.sleep(2);
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            System.out.println(queue.poll());
        }
    }
}
