package org.com.zlk.leedcode.company.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 优先级队列 PriorityQueue 默认是按二叉堆（最小堆），线程不安全
 *
 * @Author zc217
 * @Date 2020/7/1
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<Integer> minHeap = new PriorityQueue();
        minHeap.offer(4);
        minHeap.offer(1);
        minHeap.offer(3);
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());


        // 建立一个“该类的比较器”来进行排序,这个“比较器”只需要实现Comparator接口即可,然后通过该比较器对类进行排序
        PriorityQueue<Integer> maxHeap = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        maxHeap.add(4);
        maxHeap.add(1);
        maxHeap.add(3);
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());

    }

}
