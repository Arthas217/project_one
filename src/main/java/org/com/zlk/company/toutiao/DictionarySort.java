package org.com.zlk.company.toutiao;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典序排序
 * 给定n =13，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * 数据结构使用10叉树
 * 处理每一层数值和输入n做比较，有点类似递归的策略
 */
public class DictionarySort {

    public static List<Integer> dictionaryOrder(int n) {
        return similarHandler(n);
    }

    private static List<Integer> similarHandler(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i <= n) {
                list.add(i);
                add(list, n, i);
            } else {
                break;
            }
        }
        return list;
    }

    private static void add(List<Integer> list, int n, int startNum) {
        // 每往下走一层 数值扩大10倍
        startNum *= 10;
        for (int i = 0; i < 10; i++, startNum++) {
            if (startNum <= n) {
                list.add(startNum);
                add(list, n, startNum);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        List<Integer> integers = dictionaryOrder(13);
        System.out.println(integers);
        long e = System.currentTimeMillis();
        System.out.println((e - s));
    }

}
