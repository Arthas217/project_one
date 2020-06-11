package org.com.zlk.datastructure.list;

public class BasicOperationList {

    /**
     * 链表 1->2->3 有序
     * 尾插创建链表  带头节点
     * 打印链表顺序和插入元素顺序一致
     */
    public ListNode createListSort() {
        ListNode h;
        ListNode r;
        ListNode p;

        h = new ListNode();
        r = h;

        for (int i = 1; i < 6; i++) {
            p = new ListNode();
            p.value = i;
            r.next = p;
            r = p;
        }

        r.next = null;
        return h;
    }


    /**
     * 打印链表(头结点)
     */
    public void printHeadList(ListNode head) {
        ListNode r = head.next;
        while (r != null) {
            System.out.println(r.value + " ");
            r = r.next;
        }
    }

    /**
     * 打印链表（无头结点）
     */
    public void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.value + " ");
            head = head.next;
        }
    }


    /**
     * 判断链表是否存在环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode l1 = head, l2 = head.next;
        while (l1 != null && l2 != null && l2.next != null) {
            if (l1 == l2) {
                return true;
            }
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }
}
