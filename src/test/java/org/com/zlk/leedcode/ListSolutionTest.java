package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;
import org.junit.Test;

import static org.com.zlk.leedcode.ListSolution.reversePrint;

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
}