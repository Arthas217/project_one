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

    public static int num = 1;

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
        result[0] = array[0][0];
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
        int[] ints = printMatrix(a);
        for (int value : ints) {
            System.out.println(value);
        }
    }
}
