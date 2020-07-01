package org.com.zlk.company.priorityqueue;

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


        PriorityQueue<Integer> maxHeap = new PriorityQueue((Comparator<Integer>) (o1, o2) -> o2 - o1);
        maxHeap.add(4);
        maxHeap.add(1);
        maxHeap.add(3);
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());
        System.out.println(maxHeap.remove());

    }

}
