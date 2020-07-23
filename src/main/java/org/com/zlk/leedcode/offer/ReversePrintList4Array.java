package org.com.zlk.leedcode.offer;


import java.util.Stack;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 栈结构
 */
public class ReversePrintList4Array {

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) {
            val = x;
        }
    }

    public int[] reversePrint(ListNode head) {

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i <stack.size() ; i++) {
            result[i] = stack.pop().val;
        }
        return result;

    }
}
