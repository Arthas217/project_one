package org.com.zlk.datastructure.list;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zc217
 * @Date 2020/12/21
 */
public class InitListNode {

    public static ListNode init00(){
        ListNode head = new ListNode();
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);
        head.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return head;
    }
    public static ListNode init0(){
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(3);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        return l1;
    }
    public static ListNode init() {
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

    public static ListNode init2(){
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

    public static List<ListNode> init3(){
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
        return Arrays.asList(l1,l2);
    }
}
