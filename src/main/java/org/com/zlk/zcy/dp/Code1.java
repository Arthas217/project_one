package org.com.zlk.zcy.dp;

import java.util.HashMap;

/**
 * P196 换钱数
 *
 * @Author zc217
 * @Date 2020/7/9
 */
public class Code1 {


    private static HashMap<String, Integer> map = new HashMap<>();

    /**
     * @param arr 币值面额种类
     * @param aim 目标钱数
     * @return
     */
    public static int coins(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 暴力递归方式 尝试、试法
        return process1(arr, 0, aim);
    }

    /**
     * @param arr   面值数组
     * @param index 数组index位置币值面额，可以任意使用index及其之后所有的钱
     * @param aim   目标钱数
     * @return 返回方法数
     */
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            // i代表当前币值面额张数
            for (int i = 0; arr[index] * i <= aim; i++) {
                // 递归子任务 index + 1代表下一个币值面额
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * index 和 aim 两个参数 决定返回值
     * 无后效性：到达某个状态和怎么达到此状态的路径没有关系
     * map保存之前算过的问题，防止重复计算   （记忆化搜索）
     *
     * @param index
     * @param aim
     * @return
     */
    public static int process_map(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                int nextAim = aim - arr[index] * i;
                String key = index + 1 + "_" + nextAim;
                if (map.containsKey(key)) {
                    res += map.get(key);
                } else {
                    res += process1(arr, index + 1, nextAim);
                }
            }
        }
        map.put(index + "_" + aim, res);
        return res;
    }


    /**
     * 时间复杂度 O(arr长度 * aim)
     * 空间复杂度 O(arr长度 * aim)
     */
    public static int coinsDP(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        //dp矩阵值含义：使用 arr[0..i]货币的情况下，组成钱数为j有多少中方法
        int[][] dp = new int[arr.length][aim + 1];

        // 第一列：无论哪种货币情况，组成钱数为0的方法，都是1
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }

        // 第一行：使用数组中arr[0]货币面值情况下，组成钱数为j,方法只有j是arr[0]的倍数情况下为一种
        // 记忆化搜索 --枚举值
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }

        // 其他位置 这里从i=1,j=1开始 && 第一行都有值
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                // 不使用arr[i]面值, 使用其他arr[0..i-1]面值，组成钱数j，方法数为dp[i-1][j]
                dp[i][j] = dp[i - 1][j];
                // 使用arr[i]面值，直到第k张情况，方法的累加值是dp[i][j - arr[i]]，省却所有枚举的情况
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        // 返回的最终目标是右下角
        return dp[arr.length - 1][aim];
    }


    public static void main(String[] args) {
        int[] coins = {5, 3, 2};
        int aim = 10;
        int result1 = process1(coins, 0, aim);
        int result2 = process_map(coins, 0, aim);
        int result3 = coinsDP(coins, aim);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
