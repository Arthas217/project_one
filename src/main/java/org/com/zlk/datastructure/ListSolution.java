package org.com.zlk.datastructure;

import org.com.zlk.datastructure.list.ListNode;


/**
 * 链表专题
 * https://lucifer.ren/blog/2020/11/08/linked-list/
 *
 * @Author zc217
 * @Date 2020/7/23
 */
public class ListSolution {

    /**
     * 环入口的位置
     * https://blog.csdn.net/sinat_35261315/article/details/79205157
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
     * 反转链表 带头结点（迭代）
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


    // 92. 反转链表 II 带头结点 (反转从位置 m 到 n 的链表。请使用一趟扫描完成反转) 反转链表的一部分
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

}
