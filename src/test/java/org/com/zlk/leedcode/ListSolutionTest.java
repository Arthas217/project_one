package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;
import org.junit.Test;

import static org.com.zlk.datastructure.list.ListNode.*;
import static org.com.zlk.leedcode.ListSolution.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class ListSolutionTest {

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

    private ListNode init2(){
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
        l6.next = null;
        return l1;
    }

    @Test
    public void testInvertLinkedList() {
        ListNode head = new ListNode();
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
//        ListNode listNode = invertLinkedList(l1);

//        ListNode listNode2 = reverseList(l1);
//        ListNode.printList(listNode2);

//        head.next = l1;
//        ListNode.printHeadList(head);
//        ListNode listNode3 = reverseHeadList(head);
//        ListNode.printHeadList(listNode3);

//        try {
//            head.next = l1;
//            ListNode listNode = reverseBetweenHeadList(head, 2, 4);
//            ListNode.printHeadList(listNode);
//            // 不带头指针
////            ListNode listNode = reverseBetweenHeadList(l1, 2, 4);
////            ListNode.printList(listNode);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

//        head.next = l1;
//        ListNode l5 = new ListNode(5);
//        l4.next = l5;
//        ListNode listNode = reverseHeadKGroup(head, 2);
//        ListNode.printHeadList(listNode);

        ListNode l5 = new ListNode(5);
        l4.next = l5;
        ListNode listNode = reverseKGroup(l1,2);
        printList(listNode);
    }

    @Test
    public void testHasCycle() {
        boolean b = ListSolution.hasCycle(init());
        System.out.println(b);
    }

    @Test
    public void testCycleLocation() {
        ListNode listNode = ListSolution.cycleLocation(init());
        System.out.println("环所在位置的val：" + listNode.val);
    }

    @Test
    public void testCycleLen() {
        int len = ListSolution.cycleLen(init());
        System.out.println("环的长度：" + len);

    }

    @Test
    public void testReversePrint() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        int[] res = reversePrint(n1);
        for (int n : res) {
            System.out.print(n + "\t");
        }
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(4);
        ListNode l12 = new ListNode(1);
        ListNode l123 = new ListNode(8);
        ListNode l1234 = new ListNode(4);
        ListNode l12345 = new ListNode(5);
        l1.next = l12;
        l12.next = l123;
        l123.next = l1234;
        l1234.next = l12345;

        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(0);
        ListNode l223 = new ListNode(1);
        l2.next = l22;
        l22.next = l223;
        l223.next = l123;
        l123.next = l1234;
        l1234.next = l12345;

        boolean commonNode = getIntersectionNode(l1, l2);
        ListNode value = getIntersectionNode2(l1, l2);
        System.out.println(commonNode);
        System.out.println(value.val);
    }


    @Test
    public void testreverseBetween(){
        ListNode init = init2();
        ListNode head = reverseBetween(init, 2, 3);
        ListNode.printList(head);
    }
}