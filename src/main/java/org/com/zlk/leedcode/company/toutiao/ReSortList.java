package org.com.zlk.leedcode.company.toutiao;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排链表
 * 1->2->3->4->5
 * 1->5->2->4->3
 */
public class ReSortList {

    /**
     * 链表结构
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 存储空间存放元素O(n)
     */
    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 两个指针位置
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        // 排序后链表的尾处理
        list.get(i).next = null;
    }

    public static void print(ListNode head){
        while (head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        print(l1);
        // TODO
        reorderList(l1);
        System.out.println("--------");
        print(l1);

    }
}
