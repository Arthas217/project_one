package org.com.zlk.leedcode.company;

import java.util.PriorityQueue;

/**
 * 优先队列实现  数组中第二大的数值
 * @Author zc217
 * @Date 2020/7/4
 */
public class SecondMaxNum {

    public static int findKthLargest(int[] nums, int k) {
        // 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int val : nums) {
            queue.add(val);
            if (queue.size() > k)
                queue.poll();
        }
        return queue.peek();
    }

    public static void main(String[] args) {

        int[] arr = {1, 4, 124, 12, 23, 532, 6, 4536};
        System.out.println(findKthLargest(arr, 2));

    }
}
