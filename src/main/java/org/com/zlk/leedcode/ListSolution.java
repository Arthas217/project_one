package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.BasicOperationList;
import org.com.zlk.datastructure.list.ListNode;

import java.util.Stack;


/**
 * 链表专题
 *
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


    /**
     * 160. 相交链表（找到两个单链表相交的起始节点）  面试题 02.07. 链表相交
     */
    public static boolean getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return false;
        }
        // 相交点之后的长度是相同的,
        int l1_len = BasicOperationList.getListLen(headA);
        int l2_len = BasicOperationList.getListLen(headB);
        // 消除两个链表的长度差。注意两个链表长度相同情况
        ListNode m1 = l1_len - l2_len >= 0 ? headA : headB;
        ListNode m2 = l1_len - l2_len < 0 ? headA : headB;
        int d = Math.abs(l1_len - l2_len);
        while (m1.next != null && d > 0) {
            m1 = m1.next;
            d--;
        }
        while (m1 != null && m2 != null) {
            if (m1.val == m2.val && m1 == m2) {
                System.out.println(m1.val);
                return true;
            }
            m1 = m1.next;
            m2 = m2.next;
        }
        return false;
    }

    /**
     * 160.相交链表值
     * 参考https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
     * 指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
     * 如果 pA 到了末尾，则 pA = headB 继续遍历
     * 如果 pB 到了末尾，则 pB = headA 继续遍历
     * 比较长的链表指针指向较短链表head时，长度差就消除了
     * 如此，只需要将最短链表遍历两次即可找到位置
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

}
