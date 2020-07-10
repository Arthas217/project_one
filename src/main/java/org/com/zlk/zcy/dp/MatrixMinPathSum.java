package org.com.zlk.zcy.dp;

/**
 * 矩阵的最小路径和
 *
 * @Author zc217
 * @Date 2020/7/9
 */
public class MatrixMinPathSum {

    /**
     * 时间复杂度O（row *col）
     * 空间复杂度O（row *col）
     * @return
     */
    public static int minPathSum(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        // 第一列 (元素上边+元素值） 不依赖位置
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        // 第一行 （元素左边+元素值）不依赖位置
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        // 位置依赖
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1]; //target
    }

    //todo  空间压缩

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        int minPathSum = minPathSum(matrix);
        System.out.println(minPathSum);
    }
}
