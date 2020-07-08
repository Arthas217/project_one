package org.com.zlk.zcy;

/**
 * 插入排序
 * 类似调整有序的扑克牌，抓拍后找到插入位置
 *
 * @Author zc217
 * @Date 2020/7/8
 */
public class InsertionSort {

    public static void insertionSort(int[] arr) {
        ZcyUtil.arrValid(arr);
        // 默认第0的位置是已经排好序的元素
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                ZcyUtil.swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int testTime = 10;
        int maxSize = 10;
        int maxValue = 10;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ZcyUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ZcyUtil.copyArray(arr1);
            ZcyUtil.printArray(arr2);
            insertionSort(arr1);
            ZcyUtil.printArray(arr1);

        }
    }
}
