package org.com.zlk.datastructure.list;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val; //节点值

    public ListNode next; //指向节点指针

    public ListNode() {
    }

    public ListNode(int value) {
        this.val = value;
    }


    /**
     * 打印链表(头结点)
     */
    public static void printHeadList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode r = head.next;
        while (r != null) {
            result.add(r.val);
            r = r.next;
        }
        System.out.println(result);
    }

    /**
     * 打印链表（无头结点）
     */
    public static void printList(ListNode node) {
        List<Integer> result = new ArrayList<>();
        while (node != null) {
            result.add(node.val);
            node = node.next;
        }
        System.out.println(result);
    }

    /**
     * 获取链表长度(无头结点）
     */
    public static int getListLen(ListNode l) {
        int sum = 0;
        while (l != null) {
            l = l.next;
            sum++;
        }
        return sum;
    }


    /**
     * 折半查找法找到一个元素在数组中的下标
     * 如：[1,4,6,7,10,11,15]，查找8在数组中的位置，如果存在则返1，不存在则返回在7这个下标+1的这个位置上，也就是说在返回10所在位置上的下标
     */
    public static int arrayIndexOf(int[] array,int key) {
        int min,max,mid;
        min = 0;
        max = array.length - 1;

        while(min <= max) {

            mid = (min + max) >> 1;

            if (key > array[mid]) {
                min = mid + 1;
            } else if (key < array[mid]) {
                max = mid - 1;
            } else {
                return mid;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[] a = {1,4,6,7,10,11};
        System.out.println(arrayIndexOf(a, 8));
    }
}
