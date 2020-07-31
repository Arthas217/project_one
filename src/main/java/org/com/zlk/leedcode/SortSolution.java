package org.com.zlk.leedcode;

import org.com.zlk.leedcode.zcy.ZcyUtil;

import java.util.Arrays;

/**
 * 排序专题
 *
 * @Author zc217
 * @Date 2020/7/23
 */
public class SortSolution {

    //归并排序
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 递归处理分治思想  把问题都拆分为一个元素，或者是二个元素的子数组
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // 对各个拆分完的子数组进行归并
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;// 新数组位置
        // 把较小的数先移到新数组中
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // 右边到边界，把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // 把数组temp中的数覆盖arr数组
        for (int k1 = 0; k1 < temp.length; k1++) {
            // 注意arr数组下标以left为基准
            arr[left + k1] = temp[k1];
        }
    }


    // 冒泡排序 : 一次排序后最大的放在最后一个位置
    // 时间复杂的o(n*n)
    public static void bubbleSort(int[] arr) {
        // 合法性判断
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

    // 插入排序
    // 类似调整有序的扑克牌，抓拍后找到插入位置
    // 时间复杂度 最好：有序数组 o(n)  最坏：逆序 o(n*n)
    public static void insertionSort(int[] arr) {
        ZcyUtil.arrValid(arr);
        // 默认第0的位置是已经排好序的元素
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                ZcyUtil.swap(arr, j, j + 1);
            }
        }
    }

    // 选择排序 时间复杂度o(n*n)
    // * 第一次 0到n-1上选择一个最小的值，放到第0的位置
    // * 第二次 1到n-1上选择一个最小的值，放到第1的位置
    // * ...
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


    public static void main(String[] args) {
        int arr[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

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