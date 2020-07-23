package org.com.zlk.leedcode.zcy;

/**
 * 冒泡排序
 * 一次排序后最大的放在最后一个位置
 *
 * 时间复杂的o(n*n)
 * @Author zc217
 * @Date 2020/7/8
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        ZcyUtil.arrValid(arr);
        // 一趟排序的次数
        for (int end = arr.length - 1; end > 0; end--) {
            // 一趟中比较两个相邻位置数大小，如果i>i+1 就交换，i++
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    ZcyUtil.swap(arr, i, i + 1);
                }
            }
        }
    }

}
