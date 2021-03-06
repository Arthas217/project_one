package org.com.zlk.leedcode.zcy;

/**
 * 数组中元素都是正整数，求子数组中和为sum>0的最长子数组的长度
 * 时间复杂度O(N)
 *
 * @Author zc217
 * @Date 2020/7/15
 */
public class LongesSubArraySumInPositiveArray {

    public static int getMaxLen(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        // 使用双指针（单调性， 窗口扩大一定增加，窗口缩小一定减少
        int L = 0;
        int R = 0;
        // 累加值
        int sum = arr[0];
        // 最大长度值
        int len = 0;
        while (R < arr.length) {
            if (sum == k) {
                len = Math.max(len, R - L + 1);
                sum -= arr[L++];
            } else if (sum < k) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                sum -= arr[L++];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {8, 1, 3, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1};
        int result = getMaxLen(arr, 6);
        System.out.println(result);
    }
}
