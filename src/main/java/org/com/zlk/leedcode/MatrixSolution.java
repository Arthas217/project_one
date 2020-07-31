package org.com.zlk.leedcode;

/**
 * @Author zc217
 * @Date 2020/7/23
 */
public class MatrixSolution {

    // 剑指 Offer 29. 顺时针打印矩阵
    // 参考左程云思路
    private static int[] printMatrixZLK(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        // 左上角
        int lx = 0;
        int ly = 0;
        // 右下角
        int rx = row - 1;
        int ry = col - 1;
        int[] result = new int[row * col];
        while (lx <= rx && ly <= ry) {
            printCircleValue(matrix, lx++, ly++, rx--, ry--, result);
        }
        return result;
    }
    private static void printCircleValue(int[][] matrix, int lx, int ly, int rx, int ry, int[] result) {
        if (lx == rx) {
            //子矩阵只有一行
            for (int j = ly; j <= ry; j++) {
                result[num++] = matrix[lx][j];
            }
        } else if (ly == ry) {
            //子矩阵只有一列
            for (int i = lx; i <= rx; i++) {
                result[num++] = matrix[i][ly];
            }
        } else {
            // 一般情况
            int tempx = lx;
            int tempy = ly;

            // 打印一圈矩阵的上行
            while (tempy != ry) {
                result[num++] = matrix[lx][tempy];
                tempy++;
            }
            // 打印一圈矩阵的右列
            while (tempx != rx) {
                result[num++] = matrix[tempx][ry];
                tempx++;
            }
            // 打印一圈矩阵的下行
            while (tempy != lx) {
                result[num++] = matrix[rx][tempy];
                tempy--;
            }
            // 打印一圈矩阵的左列
            while (tempx != lx) {
                result[num++] = matrix[tempx][ly];
                tempx--;
            }
        }
    }





    // dr和dc可以确定移动方向顺序
    // 行方向
    public static int dr[] = {0, 1, 0, -1};
    // 列方向
    public static int dc[] = {1, 0, -1, 0};
    public static int num = 0;
    // 自己参考的一个视频 dfs回溯方法
    public static int[] printMatrix(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int row = array.length;
        int col = array[0].length;
        int[][] visited = new int[row][col];
        int[] result = new int[row * col];

        int i = 0;
        int j = 0;
        visited[0][0] = 1;
        result[num++] = array[0][0];
        direct(array, row, col, visited, i, j, result, 0);
        return result;
    }
    public static void direct(int[][] array, int row, int col, int[][] visited, int i, int j, int[] result, int priority) {
        // 优先以priority值为基本方向
        for (int d = priority; d < 4; d++) {
            // 顺时针
            int d_row = i + dr[d];
            int d_col = j + dc[d];
            // 判断d方向边界合法性
            if (d_row < 0 || d_row >= row || d_col < 0 || d_col >= col || visited[d_row][d_col] == 1) {
                if (priority == 3) {
                    // 如果向上方向寻找失败，则需要回退到原来的位置
                    direct(array, row, col, visited, i, j, result, 0);
                }
                continue;
            }
            result[num++] = array[d_row][d_col];
            visited[d_row][d_col] = 1;
            // 赋值目的 下一次方向的确定是以上一个方向为准。如果此方向合法性失败的话，那么在以d=0方向继续找
            priority = d;
            direct(array, row, col, visited, d_row, d_col, result, priority);
        }
    }


    // 剑指 Offer 04. 二维数组中的查找
    // 数组每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        // 右上角元素
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//        int[] value = printMatrix(a);
        int[] value = printMatrixZLK(a);
        for (int num : value) {
            System.out.println(num);
        }

        int[][] matrix = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        System.out.println(findNumberIn2DArray(matrix, 7));
    }
}