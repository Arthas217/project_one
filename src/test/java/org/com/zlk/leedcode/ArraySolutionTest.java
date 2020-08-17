package org.com.zlk.leedcode;

import org.junit.Test;

import static org.com.zlk.leedcode.ArraySolution.*;

/**
 * @Author zc217
 * @Date 2020/8/5
 */
public class ArraySolutionTest {

    @Test
    public void testMaximum69Number() {
        int num = 9996;
        //整数num转为整数数组，用/和%来判断6数字位置，然后还得需要转回整数（好复杂）
        int i = maximum69Number(num);
        System.out.println(i);

    }

    @Test
    public void testMaximum69Number2() {
        // 整数num转为字符串
        int num = 9996;
        int i1 = maximum69Number2(num);
        System.out.println(i1);
    }

    @Test
    public void testAverage() {
        int[] salary = {4000, 3000, 1000, 2000};
        double aver = average(salary);
        System.out.println(aver);
    }

    @Test
    public void testSingleNumber() {
        int[] arr = {4, 1, 2, 1, 2, 4, 3, 7, 3};
        int[] arr2 = {1, 2, -6, 1, 1, 2, 2, 3, 3, 3};
        int[] arr3 = {1, 2, 1, 3, 2, 5};
        System.out.println(singleNumber(arr));
    }

    @Test
    public void testSingleNumberOfHashtable() {
        int[] arr = {4, 1, 2, 1, 2, 4, 3, 7, 3};
        System.out.println(singleNumberOfHashtable(arr));
    }

    @Test
    public void testSingleNumber2() {
        int[] arr2 = {1, 2, -6, 1, 1, 2, 2, 3, 3, 3};
        System.out.println(singleNumber2(arr2));
    }

    @Test
    public void testSingleNumber3() {
        int[] arr3 = {1, 2, 1, 3, 2, 5};
        int[] res = singleNumber3(arr3);
        for (int n : res) {
            System.out.println(n);
        }
    }

    @Test
    public void singleNumber33() {
        int[] arr3 = {1, 2, 1, 3, 2, 5};
        int[] res = singleNumber3(arr3);
        for (int n : res) {
            System.out.println(n);
        }
    }

    @Test
    public void testMaxSumQueue4K() {
        int[] arr4 = {3, 1, 6, 2, 1, 2};
        int[] arr5 = {3, 1, 4, 2, 2, 4};
        System.out.println(maxSumQueue4K(arr4, 3));
        System.out.println(maxSumQueue4K(arr5, 3));
    }

    @Test
    public void testMaxSumArray4K() {
        int[] arr4 = {3, 1, 6, 2, 1, 2};
        int[] arr5 = {3, 1, 4, 2, 2, 4};
        System.out.println(maxSumArray4K(arr4, 3));
        System.out.println(maxSumArray4K(arr5, 3));

    }

    @Test
    public void testTwoSum() {
        int[] arr = {2, 7, -11, 15};
        int target = -9;
        int[] twoSum = twoSum(arr, target);
        for (int value : twoSum) {
            System.out.print(value + "\t");
        }
    }

    @Test
    public void findMedianSortedArrays() {
    }

    @Test
    public void testRetainAll() {
        int[] a = {0, 1, 2, 3, 4, 5};
        int[] b = {1, 3, 5, 7, 9};
        int[] ints = retainAll(a, b);
        for (int num : ints) {
            System.out.print(num + "\t");
        }
    }

    @Test
    public void b(){
        char[] char1={'a','b','d','e'};
        char[] char2={'d','e','f'};
//        char[] char1={'a','b','d','e','f'};
//        char[] char2={'c','d','e','f'};
//        char[] char1={'a','b','d','f','g'};
//        char[] char2={'d','e','f','g'};

//        char c = find(char1,char2);
        char c2 = find2(char1,char2);
        System.out.println(c2);
    }
}