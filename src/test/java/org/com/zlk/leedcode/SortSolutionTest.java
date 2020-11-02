package org.com.zlk.leedcode;

import org.com.zlk.leedcode.zcy.ZcyUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.com.zlk.leedcode.SortSolution.insertionSort;
import static org.com.zlk.leedcode.SortSolution.mergeSort;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class SortSolutionTest {

    @Test
    public void testMergeSort() {
        int arr[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void bubbleSort() {
    }

    @Test
    public void testInsertionSort() {
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

    @Test
    public void testSelectionSort() {
    }

    @Test
    public void testQuickSort() {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49};
        SortSolution.quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
    }
}