package org.com.zlk.datastructure.list;

public class BasicOperationList {

    /**
     * 链表 1->2->3 有序
     * 尾插创建链表  带头节点
     * 打印链表顺序和插入元素顺序一致
     */
    public static ListNode createListSort() {
        ListNode h;
        ListNode r;
        ListNode p;

        h = new ListNode();
        r = h;

        for (int i = 1; i < 6; i++) {
            p = new ListNode();
            p.val = i;
            r.next = p;
            r = p;
        }

        r.next = null;
        return h;
    }

}
