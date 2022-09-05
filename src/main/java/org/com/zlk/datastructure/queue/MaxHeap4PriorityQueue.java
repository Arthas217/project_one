package org.com.zlk.datastructure.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/5 16:33
 */
public class MaxHeap4PriorityQueue {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    public static PriorityQueue<Integer> getMaxHeap() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        return maxHeap;
    }
}
