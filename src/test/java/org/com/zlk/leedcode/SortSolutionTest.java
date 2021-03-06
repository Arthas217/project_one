package org.com.zlk.leedcode;

import org.com.zlk.datastructure.SortSolution;
import org.com.zlk.leedcode.company.toutiao.TestUtil;
import org.com.zlk.leedcode.zcy.ZcyUtil;
import org.junit.Test;

import java.util.Arrays;

import static org.com.zlk.datastructure.SortSolution.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class SortSolutionTest {



    @Test
    public void testBubbleSort() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        int[] array2 = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        bubbleSort2(array2);
        System.out.println(Arrays.toString(array2));
        int[] array3 = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        bubbleSort3(array3);
        System.out.println(Arrays.toString(array2));
    }

    @Test
    public void testInsertionSort2() {
        int[] arr = {12, 1, 3, 46, 5, 0, -3, 12, 35, 16};
        insertionSort2(arr);
        System.out.println(Arrays.toString(arr));
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
        TestUtil.printArrayValue(arr);
    }

    @Test
    public void testarrayIndexOf(){
        int[] a = {1,4,6,7,10,11,15};
        int index = arrayIndexOf(a, 7);
        int location = arrayIndexOf(a, 9);
        System.out.println(index);
        System.out.println(location);
    }
}