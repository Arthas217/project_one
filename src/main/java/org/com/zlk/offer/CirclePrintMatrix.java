package org.com.zlk.offer;

/**
 * 顺时针打印矩阵
 */
public class CirclePrintMatrix {

    // dr和dc可以确定移动方向顺序
    // 行方向
    public static int dr[] = {0, 1, 0, -1};
    // 列方向
    public static int dc[] = {1, 0, -1, 0};

    public static int num = 0;

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

    private static void direct(int[][] array, int row, int col, int[][] visited, int i, int j, int[] result, int priority) {
        for (int d = priority; d < 4; d++) {
            // 顺时针
            int d_row = i + dr[d];
            int d_col = j + dc[d];
            // 判断d方向边界合法性
            if (d_row < 0 || d_row >= row || d_col < 0 || d_col >= col || visited[d_row][d_col] == 1) {
                if (priority == 3) {
                    direct(array, row, col, visited, i, j, result, 0);
                }
                continue;
            }
            result[num++] = array[d_row][d_col];
            visited[d_row][d_col] = 1;
            priority = d;
            direct(array, row, col, visited, d_row, d_col, result, priority);
        }
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[] value = printMatrix(a);
//        int[] value1 = printMatrixZLK(a);
        for (int num : value) {
            System.out.println(num);
        }
    }


    /**
     * 参考左程云思路
     */
    private static int[] printMatrixZLK(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 左上角
        int lu_x = 0;
        int lu_y = 0;
        // 右下角
        int ru_x = row - 1;
        int ru_y = col - 1;
        int[] result = new int[row * col];
        while (lu_x <= ru_x && lu_y < ru_y) {
            printCircleValue(matrix, lu_x++, lu_y++, ru_x--, ru_y--, result);
        }
        return result;
    }

    private static void printCircleValue(int[][] matrix, int lx, int ly, int rx, int ry, int[] result) {
        //子矩阵只有一行
        if (lx == rx) {
            for (int j = rx; j < ry; j++) {
                result[num++] = matrix[lx][j];
            }
        } else if (ly == ry) {
            //子矩阵只有一列
            for (int i = lx; i < ly; i++) {
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
            // 打印一圈矩阵的左行
            while (tempx != lx) {
                result[num++] = matrix[tempx][ly];
                tempx--;
            }
        }
    }
}
