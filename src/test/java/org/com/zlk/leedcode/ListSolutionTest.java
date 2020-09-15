package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;
import org.junit.Test;

import static org.com.zlk.leedcode.ListSolution.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class ListSolutionTest {

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
}