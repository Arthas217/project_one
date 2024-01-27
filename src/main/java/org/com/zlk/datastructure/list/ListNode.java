package org.com.zlk.datastructure.list;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val; //节点值

    public ListNode next; //指向下一节点指针

    public ListNode() {
    }

    public ListNode(int value) {
        this.val = value;
    }


    /**
     * 打印链表(头结点)
     */
    public static void printHeadList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode r = head.next;
        while (r != null) {
            result.add(r.val);
            r = r.next;
        }
        System.out.println(result);
    }

    /**
     * 打印链表（无头结点）
     */
    public static void printList(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        System.out.println(result);
    }

    /**
     * 获取链表长度(无头结点）
     */
    public static int getListLen(ListNode l) {
        int sum = 0;
        while (l != null) {
            l = l.next;
            sum++;
        }
        return sum;
    }
}
