package org.com.zlk.leedcode;

import org.com.zlk.datastructure.ListSolution;
import org.com.zlk.datastructure.list.InitListNode;
import org.com.zlk.datastructure.list.ListNode;
import org.junit.Before;
import org.junit.Test;


/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class ListSolutionTest {

    InitListNode initListNode;
    ListSolution listSolution;

    @Before
    public void setUp() {
        initListNode = new InitListNode();
        listSolution = new ListSolution();
    }

    @Test
    public void testInvertLinkedList() {
        ListNode.printList(listSolution.invertLinkedList(initListNode.init0()));
        ListNode.printList(listSolution.reverseList(initListNode.init0()));
        ListNode.printHeadList(listSolution.reverseHeadList(initListNode.init00()));
    }


    @Test
    public void testCycleLocation() {
        System.out.println("环所在位置的val：" + ListSolution.cycleLocation(initListNode.init()).val);
    }

    @Test
    public void testCycleLen() {
        System.out.println("环的长度：" + ListSolution.cycleLen(initListNode.init()));

    }

    @Test
    public void testReversePrint() {
        int[] res = listSolution.reversePrint(initListNode.init0());
        for (int n : res) {
            System.out.print(n + "\t");
        }
    }



    @Test
    public void testreverseBetween() {
        ListNode.printList(listSolution.reverseBetween(initListNode.init2(), 2, 3));
    }

    @Test
    public void testreverse() {
        ListNode head = new ListNode();
        head.next = initListNode.init2();
        ListNode.printHeadList(listSolution.reverseHeadKGroup(head, 2));


    }
}