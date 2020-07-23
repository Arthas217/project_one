package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;

import java.util.Stack;


/**
 * 链表专题
 * @Author zc217
 * @Date 2020/7/23
 */
public class ListSolution {

    // 剑指 Offer 06. 从尾到头打印链表（用数组返回）。
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop().val;
        }
        return result;
    }
}
