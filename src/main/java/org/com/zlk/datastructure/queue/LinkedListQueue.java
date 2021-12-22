package org.com.zlk.datastructure.queue;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 基于链表实现的队列(非循环)
 * https://github.com/wangzheng0822/algo/blob/master/java/09_queue/QueueBasedOnLinkedList.java
 * @Date 2021/12/21 20:26
 */
public class LinkedListQueue {

    /**
     * 队列对首
     */
    Node head = null;
    /**
     * 队列队尾
     */
    Node tail = null;

    /**
     * 入队列
     *
     * @param value
     */
    public void enqueue(String value) {
        if (tail == null) {
            Node newN = new Node(value, null);
            head = newN;
            tail = newN;
        } else {
            tail.next = new Node(value, null);
            tail = tail.next;
        }
    }


    /**
     * 出队列
     * @return
     */
    public String dequeue(){
        if (head==null) {
            return null;
        }
        String data = head.data;
        head = head.next;
        if(head ==null){
            tail =null;
        }
        return data;
    }


    public void printAll() {
        Node t = head;
        while (t != null) {
            System.out.println(t.data + " ");
            t = t.next;
        }
        System.out.println();
    }

    private static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }

}
