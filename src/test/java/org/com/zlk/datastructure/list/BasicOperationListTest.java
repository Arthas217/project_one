package org.com.zlk.datastructure.list;

import org.junit.Test;


/**
 * @Author zc217
 * @Date 2020/9/15
 */
public class BasicOperationListTest {

    BasicOperationList basicOperationList = new BasicOperationList();


    @Test
    public void testHasCycle() {
        boolean b = basicOperationList.hasCycle(init());
        System.out.println(b);
    }

    @Test
    public void testCycleLocation() {
        ListNode listNode = basicOperationList.cycleLocation(init());
        System.out.println("环所在位置的val：" + listNode.val);
    }

    @Test
    public void testCycleLen() {
        int len = basicOperationList.cycleLen(init());
        System.out.println("环的长度：" + len);

    }


    private ListNode init() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l3;
        return l1;
    }

}