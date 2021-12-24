package org.com.zlk.datastructure;

import org.com.zlk.leedcode.zcy.ZcyUtil;

/**
 * 排序专题
 *
 * @Author zc217
 * @Date 2020/7/23
 */
public class SortSolution {


    /**
     * 折半查找法找一个元素在数组中的下标
     * key 要查找的元素
     * 找到则返回元素在数组中的下标，如果没找到，则返回这个元素在有序数组中的位置
     * 如：[1,4,6,7,10,11,15]，查找8在数组中的位置，不存在则返回10所在位置的下标 查找6存在则返回该数位置下标，
     */
    public static int arrayIndexOf(int[] array, int key) {
        int min = 0, max = array.length - 1;
        int mid;
        while (min <= max) {
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



    // 冒泡排序 : 一次排序后最大的放在最后一个位置
    // 时间复杂的o(n*n)
    public static void bubbleSort(int[] arr) {
        // 排序总趟数
        for (int end = arr.length - 1; end > 0; end--) {
            // 一趟中比较两个相邻位置数大小，如果i>i+1 就交换，i++
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    ZcyUtil.swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        int temp;
        // 外部循环控制所有的回合
        for (int i = 0; i < arr.length; i++) {
            // 内部循环代表每一轮的冒泡处理，先进行元素比较，再进行元素交换。
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort3(int[] arr) {
        int temp;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }


    /**
     * 好、坏、平均   O(n) 、O(n2)、O(n2)
     * @param arr
     * @param n
     */
    public void bubbleSort4JIKE(int[] arr,int n){
        if (n <= 1) {
            return;
        }
        // 遍历次数
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - j; j++) {
                if (arr[j] > arr[j + 1]) { // 比较
                    // 交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true; // 标识有数据交换
                }
            }
            if (!flag) {  //没有数据交换，提前退出
                break;
            }
        }
    }


    // 插入排序 ：类似调整有序扑克牌，抓牌后找到插入位置
    // 时间复杂度: 最好有序数组 o(n) 最坏逆序 o(n*n) 平均时间复杂度是O（n^2） 空间复杂度:o(1)
    // 1.在大多数元素已经有序的情况下，插入排序的工作量较小
    // 2.在元素数量较少的情况下，插入排序的工作量较小
    public static void insertionSort(int[] arr) {
        // 默认第0的位置是已经排好序的元素
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                ZcyUtil.swap(arr, j, j + 1);
            }
        }
    }

    public static void insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int j = i - 1;
            // 从右向左比较元素的同时，进行元素复制
            for (; j >= 0 && arr[j] > insertVal; j--) {
                arr[j + 1] = arr[j];
            }
            // 遍历循环后还有j--操作
            arr[j + 1] = insertVal;
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

    // 快排 时间复杂度o(n*n) o(n*logn)
    // 参考https://wiki.jikexueyuan.com/project/easy-learn-algorithm/fast-sort.html
    public static void quickSort(int[] arr) {
        helpSort(arr, 0, arr.length - 1);
    }

    private static void helpSort(int[] arr, int left, int right) {
        //一趟排序通过交换元素构建分界点p
        int p = partition(arr, left, right);
        if (p < 0) {
            return;
        }
        //左右递归
        helpSort(arr, left, p - 1);
        helpSort(arr, p + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        //哨兵
        int sentinel = arr[left];
        int i = left, j = right;
        int t;
        while (i != j) {
            while (arr[j] >= sentinel && i < j) {
                j--;
            }
            while (arr[i] <= sentinel && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        // i和j重合，最终将基准数归位
        arr[left] = arr[i];
        arr[i] = sentinel;
        return i;
    }

}
