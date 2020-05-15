package org.com.zlk.leedcode.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 排列 【1，2，3】
 * 用一个DFS结构理解
 * 回溯思想
 */
public class PermutationTest {

    public static List<List<Integer>> permutaTion(int[] nums) {
        // 存储一次排列结果
        LinkedList<Integer> list = new LinkedList<>();
        // 所有排列结果
        List<List<Integer>> result = new ArrayList<>();
        // 记录访问过元素
//        int[] visited = new int[nums.length];
//        dfsMethod(nums, list, result, visited);
        dfsMethodSwp(nums, list, result, 0);
        return result;
    }

    public static void dfsMethodSwp(int[] nums, LinkedList<Integer> list, List<List<Integer>> result, int pos) {
        if (nums.length == list.size()) {
            result.add(new LinkedList<>(list));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            // 保证pos后面的数字是没有选中的 pos前面的数据都是选中的
            // 相当于visited过程
            list.add(nums[i]);
            // TODO
            // swap过程还不是很懂
            swap(nums, i, pos);
            dfsMethodSwp(nums, list, result, pos + 1);
            swap(nums, i, pos);
            list.removeLast();
        }
    }

    public static void swap(int[] nums, int selectNum, int pos) {
        int temp = nums[selectNum];
        nums[selectNum] = nums[pos];
        nums[pos] = temp;
    }

    public static void dfsMethod(int[] nums, LinkedList<Integer> list, List<List<Integer>> result, int[] visited) {
        if (nums.length == list.size()) {
            result.add(new LinkedList<>(list));
            return;
        }
        // 此处注意 linkedlist底层实现 用链表的结构实现了栈和队列
        // 当成栈的时候 list.push(nums[i]);链表的头入栈 list.pop() 链表的头出栈
        // 如果当成队列来使用list.offer(nums[i]); 需要使用list.removeLast();因为队列的poll是从链表头删除的
        // 每一种输出的结果顺序使是链表的头到尾值 所以操作的时候一定要看内部的逻辑
        for (int i = 0; i < nums.length; i++) {
            // 回溯思想 成对出现
            if (visited[i] == 0) {
                // 把访问过的元素放入list中
                list.add(nums[i]);
                // 标记访问过
                visited[i] = 1;
                // 进入下一层决策树
                dfsMethod(nums, list, result, visited);
                // 恢复元素标记
                visited[i] = 0;
                // 把访问过的元素移出list中
                list.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3};
        List<List<Integer>> linkedLists = permutaTion(test);
        for (List list : linkedLists) {
            System.out.println(list);
        }
    }
}
