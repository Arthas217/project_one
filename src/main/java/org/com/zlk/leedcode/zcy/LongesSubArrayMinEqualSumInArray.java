package org.com.zlk.leedcode.zcy;

/**
 * 给定数组中（可正、可负，可0），求累加和小于等于整数aim的最长子数组长度(注意不是子序列）
 *
 * @Author zc217
 * @Date 2020/7/15
 */
public class LongesSubArrayMinEqualSumInArray {

    private static int getMinEqualLen(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 数组min_sum(代表从自身位置到后面位置某位置时，数组中元素和的最小值）
        // 数组min_index（min_sum得到值时，数组下标位置）
        int[] min_sum = new int[arr.length];
        int[] min_index = new int[arr.length];
        // 数组最后一个元素赋值
        min_sum[arr.length - 1] = arr[arr.length - 1];
        min_index[arr.length - 1] = arr.length - 1;
        // min_sum和min_index 从后往前赋值
        for (int i = arr.length - 2; i >= 0; i--) {
            if (min_sum[i + 1] < 0) {
                min_sum[i] = arr[i] + min_sum[i + 1];
                min_index[i] = min_index[i + 1];
            } else {
                min_sum[i] = arr[i];
                min_index[i] = i;
            }
        }

        int R = 0;
        int sum = 0;
        int len = 0;
        for (int start = 0; start < arr.length; start++) {
            //扩窗口
            while (R < arr.length && sum + min_sum[R] < target) {
                sum += min_sum[R];
                // 此时的位置是要扩的位置
                R = min_index[R] + 1;
            }
            // 如果大于target，那个start+1； sum减掉此时位置上的值
            sum -= R > start ? arr[start] : 0;
            // 已经满足要求的len
            len = Math.max(len, R - start);
            // 第一次判断窗口没有扩大，即窗口大小为0
            R = Math.max(R, start + 1);
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {8, 1, 3, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1};
        int result = getMinEqualLen(arr, 6);
        System.out.println(result);
    }

}
