package org.com.zlk.datastructure.list;

public class BasicOperationList {

    /**
     * 链表 1->2->3 有序
     * 尾插创建链表  带头节点
     * 打印链表顺序和插入元素顺序一致
     */
    public static ListNode createListSort() {
        ListNode h;
        ListNode r;
        ListNode p;

        h = new ListNode();
        r = h;

        for (int i = 1; i < 6; i++) {
            p = new ListNode();
            p.val = i;
            r.next = p;
            r = p;
        }

        r.next = null;
        return h;
    }


    /**
     * 打印链表(头结点)
     */
    public void printHeadList(ListNode head) {
        ListNode r = head.next;
        while (r != null) {
            System.out.println(r.val + " ");
            r = r.next;
        }
    }

    /**
     * 打印链表（无头结点）
     */
    public void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val + " ");
            head = head.next;
        }
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
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

    /**
     * 判断链表是否存在环
     */
    public boolean hasCycle(ListNode head) {
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
    public ListNode cycleLocation(ListNode head) {
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
    public int cycleLen(ListNode head) {
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
     * 获取链表长度
     */
    public static int getListLen(ListNode l) {
        int sum = 0;
        while (l != null) {
            l = l.next;
            sum++;
        }
        return sum;
    }
}
