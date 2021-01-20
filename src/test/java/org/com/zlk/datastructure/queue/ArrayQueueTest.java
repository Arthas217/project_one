package org.com.zlk.datastructure.queue;

/**
 * @Author zc217
 * @Date 2020/7/31
 */
public class ArrayQueueTest {

    public static void main(String[] args) {

        ArrayQueue<Integer> arr = new ArrayQueue(Integer.class,3);
        //用数组实现一个队列
        int value1 = arr.inQueue(1);
        System.out.println(value1 > 0 ? value1 + "入队" : "不入队");
        arr.printArr();
        System.out.println("获取队列头元素：" + arr.showHead());

        int value2 = arr.inQueue(2);
        System.out.println(value2 > 0 ? value2 + "入队" : "不入队");
        arr.printArr();
        System.out.println("获取队列头元素：" + arr.showHead());

        int value3 = arr.inQueue(3);
        System.out.println(value3 > 0 ? value3 + "入队" : "不入队");
        arr.printArr();
        System.out.println("获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

//        int v4 = arr.inQueue(4);
//        System.out.println(v4 > 0 ? v4 + "入队" : "不入队");
//        arr.printArr();
//        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
//        System.out.println("队列中元素个数为：" + arr.getLen());

        System.out.println("-------------------------------------------------------");
        int outv1 = arr.outQueue();
        System.out.println(outv1 < 0 ? "不操作" : outv1 + "出队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

        int value4 = arr.inQueue(4);
        System.out.println(value4 > 0 ? value4 + "入队" : "不入队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

        System.out.println(arr.outQueue() + "出队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

        int outv2 = arr.outQueue();
        System.out.println(outv2 < 0 ? "不操作" : outv2 + "出队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

        int outv3 = arr.outQueue();
        System.out.println(outv3 < 0 ? "不操作" : outv3 + "出队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
        System.out.println("队列中元素个数为：" + arr.getLen());

        int v5 = arr.inQueue(5);
        System.out.println(v5 > 0 ? v5 + "入队" : "不入队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());

        int v6 = arr.inQueue(6);
        System.out.println(v6 > 0 ? v6 + "入队" : "不入队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());

        int v7 = arr.inQueue(7);
        System.out.println(v7 > 0 ? v7 + "入队" : "不入队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());

        int outv4 = arr.outQueue();
        System.out.println(outv4 < 0 ? "不操作" : outv4 + "出队");
        arr.printArr();
        System.out.println(arr.showHead() == null ? "" : "获取队列头元素：" + arr.showHead());
    }

}