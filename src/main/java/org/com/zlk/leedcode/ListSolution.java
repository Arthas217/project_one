package org.com.zlk.leedcode;

import org.com.zlk.datastructure.list.ListNode;
import org.com.zlk.datastructure.tree.TreeNode;

import java.util.Stack;


/**
 * 链表专题
 * https://lucifer.ren/blog/2020/11/08/linked-list/
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
        // 步骤 2: 再把原 node节点后继结点的后继结点指向 node(4)，node的后继节点设置为空(防止形成环)
        node.next.next = node;
        node.next = null;
        // 步骤 3: 返回翻转后的头结点
        return newHead;
    }

    /**
     * 反转链表（迭代）
     */
    public static ListNode reverseList(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next;
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

    /**
     * 反转链表带头结点（迭代）
     */
    public static ListNode reverseHeadList(ListNode head) {
        ListNode pre = head.next;
        ListNode cur = pre.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 此时 pre 为头结点的后继结点
        head.next = pre;
        return head;
    }

    // 92. 反转链表 II  (反转从位置 m 到 n 的链表。请使用一趟扫描完成反转)
    // 带头结点参考https://labuladong.gitbook.io/algo/labuladong-he-ta-de-peng-you-men/yi-wen-xue-hui-lian-biao-jie-ti
    public static ListNode reverseBetweenHeadList(ListNode head, int fromIndex, int toIndex) throws Exception {
        ListNode fromPre = null;            // from-1结点
        ListNode from = null;               // from 结点
        ListNode to = null;                 // to 结点
        ListNode toNext = null;             // to+1 结点
        // 步骤1：找到  from-1, from, to,  to+1 这四个结点
        ListNode tmp = head.next;
        //  不带头指针 ListNode tmp = head;
        int curIndex = 1; // 设头结点index为1
        while (tmp != null) {
            if (curIndex == fromIndex - 1) { // 从非head后继结点开始翻转
                fromPre = tmp;
            } else if (curIndex == fromIndex) {
                from = tmp;
            } else if (curIndex == toIndex) {
                to = tmp;
            } else if (curIndex == toIndex + 1) {
                toNext = tmp; // curIndex>节点个数时 toNext =null
            }
            tmp = tmp.next;
            curIndex++;
        }
        if (from == null || to == null) {
            throw new Exception("不符合条件");
        }
        // 步骤2：循环迭代法翻转从from到to的结点
        ListNode pre = from;
        ListNode cur = pre.next;
        while (cur != toNext) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 步骤3：将 from-1 节点指向 to 结点，将 from 结点指向 to + 1 结点
        if (fromPre != null) {
            fromPre.next = to;
        } else {
            head.next = to; // 如果从head的后继结点开始翻转，则需要重新设置 head 的后继结点
//            head = to; // 不带头指针
        }
        from.next = toNext;
        return head;
    }

    // 25. K个一组翻转链表  (k 个节点一组进行翻转,k 是一个正整数，它的值小于或等于链表的长度)
    public static ListNode reverseHeadKGroup(ListNode head, int k) {
        ListNode tmp = head.next;
        ListNode startKPre = head;      // k个一组链表头结点的前置结点
        ListNode startK = null;         // k个一组链表中的头结点
        ListNode endK;                  // k个一组链表中的尾结点
        int step = 0;               // 计数，用来找出首结点和尾结点
        while (tmp != null) {
            // tmp的下一个节点,因为由于翻转，tmp 的后继结点会变,要提前保存
            ListNode tmpNext = tmp.next;
            if (step == 0) { // 确定头结点位置
                startK = tmp;
                step++;
            } else if (step == k - 1) {
                endK = tmp; // 确定尾结点位置
                ListNode pre = startK;  // 对链表迭代进行翻转
                ListNode cur = startK.next;
                // 如果节点总数不是k的整数倍，将剩余节点保持原有顺序
                if (cur == null) {
                    break;
                }
                ListNode endKNext = endK.next; // 确定k个一组链表头结点的后置结点
                while (cur != endKNext) {
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }
                // 翻转后此时endK和startK 分别是是k个一组链表中的首尾结点
                startKPre.next = endK;
                startK.next = endKNext;
                // 确定下一个k个一组翻转的位置
                startKPre = startK;
                step = 0;
            } else {
                step++;
            }
            tmp = tmpNext;
        }
        return head;
    }

    // 25. K个一组翻转链表(无链表头，递归）
    // 步骤：
    // 1 找到head开始的第k的节点
    // 2 head到tail前一个结点间翻转，并返回新节点
    // 3.再以tail为头结点递归
    // 4.递归结束后，将head.next指向翻转的新节点
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {  // 剩余数量小于k的话，则不需要反转。
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseList2(head, tail);// 翻转前k个元素
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    private static ListNode reverseList2(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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


    // 501. 二叉搜索树中的众数 （出现频率最高的元素）答案可能有多个值出现的次数一样多。
    public int[] findMode(TreeNode root) {
        return null;

    }

}
