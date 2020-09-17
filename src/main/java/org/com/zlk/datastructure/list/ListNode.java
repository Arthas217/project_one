package org.com.zlk.datastructure.list;

public class ListNode {

    public int val; //节点值

    public ListNode next; //指向节点指针

    public ListNode() {
    }

    public ListNode(int value) {
        this.val = value;
    }


    /**
     * 打印链表(头结点)
     */
    public static void printHeadList(ListNode head) {
        ListNode r = head.next;
        while (r != null) {
            System.out.println(r.val + " ");
            r = r.next;
        }
    }

    /**
     * 打印链表（无头结点）
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }

    /**
     * 获取链表长度
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
