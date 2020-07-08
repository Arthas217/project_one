package org.com.zlk.zcy;

/**
 * 选择排序
 * 第一次 0到n-1上选择一个最小的值，放到第0的位置
 * 第二次 1到n-1上选择一个最小的值，放到第1的位置
 * ...
 *
 * @Author zc217
 * @Date 2020/7/8
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        ZcyUtil.arrValid(arr);
        // 遍历的次数从0到n-1
        for (int i = 0; i < arr.length - 1; i++) {
            // 初始默认i位置是最小值的下标
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 找最小数及保存此时数值下标
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            ZcyUtil.swap(arr, i, minIndex);
        }
    }
}
