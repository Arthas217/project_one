package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;

import java.util.Stack;


/**
 * 链表专题
 *
 * @Author zc217
 * @Date 2020/7/23
 */
public class ListSolution {

    /**
     * 判断链表是否存在环
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            // 2步
            fast = fast.next.next;
        }
        return false;
    }

    /**
     * 环入口的位置
     * 参考https://blog.csdn.net/sinat_35261315/article/details/79205157
     */
    public static ListNode cycleLocation(ListNode head) {
        ListNode walk = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walk = walk.next;
            runner = runner.next.next;
            if (walk == runner) {
                System.out.println("此链表有环");
                break;
            }
        }
        if (runner == null || runner.next == null) {
            return null;
        }
        ListNode cycle = head;
        ListNode corss = walk;
        while (cycle != corss) {
            cycle = cycle.next;
            corss = corss.next;
        }
        return cycle;
    }

    /**
     * 环的长度
     */
    public static int cycleLen(ListNode head) {
        ListNode cycle = cycleLocation(head);
        int len = 1;
        ListNode l = cycle;
        while (l.next != cycle) {
            len++;
            l = l.next;
        }
        return len;
    }

    /**
     * 翻转链表（递归）
     * 时间复杂度是 O(n)   调用递归函数
     * 空间复杂度也是 O(n)  压入栈
     * 4--->3--->2--->1 变成了 4--->3<---2<---1
     * 4<---3<---2<---1
     */
    public static ListNode invertLinkedList(ListNode node) {
        if (node.next == null) {
            return node;
        }
        // 步骤 1: 先翻转 node 之后的链表
        ListNode newHead = invertLinkedList(node.next);
        // 步骤 2: 再把原 node 节点后继结点的后继结点指向 node (4)，node 的后继节点设置为空(防止形成环)
        node.next.next = node;
        node.next = null;
        // 步骤 3: 返回翻转后的头结点
        return newHead;
    }

    /**
     * 反转链表（迭代）
     */
    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            //记录当前节点的下一个节点
            next = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 92. 反转链表 II  (反转从位置 m 到 n 的链表。请使用一趟扫描完成反转)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        return null;
    }

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
        int l1_len = ListNode.getListLen(headA);
        int l2_len = ListNode.getListLen(headB);
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
