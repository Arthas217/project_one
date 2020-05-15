package org.com.zlk.leedcode.permutation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CombinationTest {

    public static List<List<Integer>> combine() {
        LinkedList<Integer> list = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfsMethod2(4, 2, list, result, 1);
        return result;
    }

    /**
     * 时间复杂度2的n次方
     */
    private static void dfsMethod1(int n, int k, LinkedList<Integer> list, List<List<Integer>> result, int pos) {
        if (list.size() == k) {
            result.add(new LinkedList<>(list));
            return;
        }
        // 遍历所有位置元素时，如果不是合法元素，不需要添加到list中，但是需要返回
        if (pos > n) {
            return;
        }
        // 对于每个元素选，加入list
        list.add(pos);
        // pos+1下一个元素
        dfsMethod1(n, k, list, result, pos + 1);
        // 对于每个元素的不选，退出list
        list.removeLast();
        dfsMethod1(n, k, list, result, pos + 1);
    }

    /**
     * 时间复杂度 : O (k * Cnk )
     * 改进下 用一个循环
     */
    private static void dfsMethod2(int n, int k, LinkedList<Integer> list, List<List<Integer>> result, int pos) {
        if (list.size() == k) {
            result.add(new LinkedList<>(list));
            return;
        }
        // 选择元素后，不能选择比它小的数，保证查重、保证弹出元素不需要在dfs操作。
        // 对每个位置的元素选或者不选情况
        for (int i = pos; i <= n; i++) {
            list.add(i);
            dfsMethod2(n, k, list, result, i + 1);
            list.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> linkedLists = combine();
        for (List list : linkedLists) {
            System.out.println(list);
        }
    }
}
