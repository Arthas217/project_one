package org.com.zlk.zcy.dp;

/**
 * 最长递增子序列
 *
 * @Author zc217
 * @Date 2020/7/9
 */
public class LongIncreaseSubSeq {

    /**
     * 时间复杂度  O(N * N)
     * 1. dp数组
     * 2. 遍历dp找最大值以位置，逆序还原决策路径
     */
    public static int[] subSeq(int[] arr) {
        // 生成长度为N的dp组数
        int[] dp = getDP(arr);
        // 找出最长的递增自序列
        int[] result = getLIS(arr, dp);
        return result;
    }

    private static int[] getDP(int[] arr) {
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // dp[i] 表示在以arr[i]这个数结尾的情况下，arr[0...i-1]中的最大递增子序列长度
            dp[i] = 1;  // 说明以arr[i]结尾情况下最长递增子序列中 只包含arr[i]自己
            for (int j = 0; j < i; j++) {
                // 如果arr[0..j]中所有比arr[i]小的数都可以作为倒数第二个数（选择一个以这个数结尾的最大递增子序列值dp最大值
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

    /**
     * 1.保存最大值及在arr的位置
     * 2.找到比arr[index]小的位置i && dp[i]= dp[index] -1
     */
    private static int[] getLIS(int[] arr, int[] dp) {
        int index = 0;
        int len = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] result = new int[len];
        // 赋值最长index的元素
        result[--len] = arr[index];
        // 从index逆序遍历数组找到下一个小的元素 && 满足dp关系，然后赋值到result中
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                result[--len] = arr[i];
                index = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        int[] result = subSeq(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + "\t");
        }

    }
}
