package org.com.zlk.datastructure.list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author zc217
 * @Date 2020/12/24
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        /**
         * LinkedList 除了用作list  本身还提供了用作栈和队列的功能
         * LinkedList 用作stack
         */
        LinkedList stack = new LinkedList();
        for (int i = 0; i < 10; i++) {
            /* 入栈 */
            stack.push("栈测试" + i);
        }
        /*出栈 并移除顶部元素*/
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        /* 返回顶部元素但不弹出*/
        System.out.println(stack.peek());
        System.out.println(stack.pop());

        /**
         *  LinkedList 用作队列
         */
        Queue queue = new LinkedList<String>();
        for (int i = 0; i < 10; i++) {
            queue.offer("队列测试" + i);
        }

        /*出队列*/
        System.out.println(queue.poll());
        /*返回队首元素 但是不移除*/
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
