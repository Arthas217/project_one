package org.com.zlk.leedcode;

import org.com.zlk.leedcode.zcy.ZcyUtil;

/**
 * 排序专题
 *
 * @Author zc217
 * @Date 2020/7/23
 */
public class SortSolution {

    // 归并排序 时间复杂度o(n*logn) 空间复杂度o(n)
    // 参考：https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653200029&idx=1&sn=51ecebafb9ff77baf3de71bdc4f67b78&chksm=8c99ec47bbee6551b0377b97e26670c4895d0c934051e4aa927e62bf9b64996b6e1f7459edfe&scene=21#wechat_redirect
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 递归处理把问题都拆分为一个元素，或者是二个元素的子数组
            // 折半成两个小集合，分别进行递归
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // 对各个拆分完的子数组进行归并成一个大集合
            merge(arr, left, mid, right);
        }// 类似后序遍历位置
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArray = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int p = 0;// 新数组位置
        // 比较两个小集合的元素，把较小的数先移到新数组中(依次放入大集合)
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] < arr[p2]) {
                tempArray[p++] = arr[p1++];
            } else {
                tempArray[p++] = arr[p2++];
            }
        }
        // 右边到边界，把左边剩余的数移入数组
        // 左侧小集合还有剩余，依次放入大集合尾部
        while (p1 <= mid) {
            tempArray[p++] = arr[p1++];
        }
        // 左边到边界，把右边剩余的数移入数组
        while (p2 <= right) {
            tempArray[p++] = arr[p2++];
        }
        // 把大集合tempArray的元素复制回原数组
        for (int i = 0; i < tempArray.length; i++) {
            // 注意arr数组下标以left为基准
            arr[left + i] = tempArray[i];
        }
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

    // 希尔排序（对原始数组进行预处理，逐步分组进行粗调--希尔排序的增量--常用逐步折半的增量方法，再进行直接插入排序的思想）
    // https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653199674&idx=1&sn=9ab7bb7e465104c67a3d8590ebd0fe6c&chksm=8c99efe0bbee66f69c07e5f423d7751c9667fa82beb6dcaef4c0e96dac9545d2277c8179c765&scene=21#wechat_redirect
    public static void shellSort(int[] arr) {
        int d = arr.length;
        while (d > 1) {
            d = d / 2;  //折半
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < arr.length; i = i + d) {
                    int insertVal = arr[i];
                    int j = i - d;
                    for (; j >= 0 && arr[j] > insertVal; j = j - d) {
                        arr[j + d] = arr[j];
                    }
                    arr[j + d] = insertVal;
                }
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
