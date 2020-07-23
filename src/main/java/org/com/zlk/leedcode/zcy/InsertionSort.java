package org.com.zlk.leedcode.zcy;

/**
 * 插入排序
 * 类似调整有序的扑克牌，抓拍后找到插入位置
 * <p>
 * 时间复杂度
 * 1）最好：有序数组 o(n)
 * 2) 最坏：逆序 o(n*n)
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
        // 通过对数器验证算法的正确性
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ZcyUtil.generateRandomArray(maxSize, maxValue);
            int[] arr2 = ZcyUtil.copyArray(arr1);
            int[] arr3 = ZcyUtil.copyArray(arr1);
            insertionSort(arr1);
            ZcyUtil.correctMethod(arr2);
            if (!ZcyUtil.isEqual(arr1, arr2)) {
                succeed = false;
                ZcyUtil.printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }
}
