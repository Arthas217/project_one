package org.com.zlk.datastructure.queue;

import java.util.PriorityQueue;

/**
 * 利用最小堆获取TopK
 * 1.PriorityQueue默认最小堆
 * 2.实现MinHeap
 *
 * @Author zc217
 * @Date 2020/12/24
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueueEg();
        PriorityQueueMaxHeapEg();
        MinHeapTopK();
    }

    private static void MinHeapTopK() {
        // 源数据
        int[] data = {56, 275, 12, 6, 45, 478, 41, 1236, 456, 12, 546, 45};
        // 获取Top5
        int[] top5 = topK(data, 5);
        for (int i = 0; i < 5; i++) {
            System.out.println(top5[i]);
        }
    }

    /**
     * 自动成为一个小根堆
     */
    private static void PriorityQueueEg() {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(49);
        priorityQueue.add(38);
        priorityQueue.add(65);
        priorityQueue.add(97);
        priorityQueue.add(76);
        priorityQueue.add(13);
        priorityQueue.add(27);
        priorityQueue.add(49);
        System.out.println("将无序元素建立最小堆的大小" + priorityQueue.size());
        while (priorityQueue.size() > 0) {
            Object poll = priorityQueue.poll();
            System.out.println("输出堆顶元素" + poll + "重新调整堆");
            System.out.println("最小堆大小" + priorityQueue.size());
        }
    }

    /**
     * 自定义大顶堆测试
     */
    private static void PriorityQueueMaxHeapEg(){
        PriorityQueue<Integer> maxHeap = MaxHeap4PriorityQueue.getMaxHeap();
        maxHeap.add(49);
        maxHeap.add(38);
        maxHeap.add(65);
        maxHeap.add(97);
        maxHeap.add(76);
        maxHeap.add(13);
        maxHeap.add(27);
        maxHeap.add(49);
        System.out.println("将无序元素建立最小堆的大小" + maxHeap.size());
        while (maxHeap.size() > 0) {
            Object poll = maxHeap.poll();
            System.out.println("输出堆顶元素" + poll + "重新调整堆");
            System.out.println("最小堆大小" + maxHeap.size());
        }

    }

    /**
     * 从数组中获取最大的k个数
     */
    private static int[] topK(int[] data, int k) {
        // 先取K个元素放入数组topk中
        int[] topk = new int[k];
        for (int i = 0; i < k; i++) {
            topk[i] = data[i];
        }
        // 转换成最小堆
        MinHeap heap = new MinHeap(topk);
        // 从k开始遍历剩余元素
        for (int i = k; i < data.length; i++) {
            int root = heap.getRoot();
            // 当数据大于堆中最小的数（根节点）时，替换堆中根节点，再转换成堆
            // 目的是始终保持已经遍历过的数组中前k大的元素
            if (data[i] > root) {
                heap.setRoot(data[i]);
            }
        }
        return topk;
    }
}
