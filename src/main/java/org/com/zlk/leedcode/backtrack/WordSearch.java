package org.com.zlk.leedcode.backtrack;

public class WordSearch {

    public static int dr[] = {1, 0, -1, 0};
    public static int dc[] = {0, 1, 0, -1};

    public static boolean isEqualWord(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 匹配第一个相同的字符
                if (board[i][j] != word.charAt(i)) {
                    continue;
                }
                //标记访问过元素（当前元素）
                int[][] visited = new int[row][col];
                visited[i][j] = 1;
                // 找到匹配第一个字符位置pos  利用dfs（找解）从row|| col的四个方向去匹配字符
                if (dfs(board, word, i, j, row, col, 1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word, int i, int j, int row, int col,
                              int pos, int[][] visited) {
        // 找到word匹配  pos含义是word中字符位置
        if (pos == word.length()) {
            return true;
        }
        System.out.println(pos);
        // d代表方向
        for (int d = 0; d < 4; d++) {
            // d四个方向搜索 方向是下、右、上，左（逆时针）
            int d_row = i + dr[d];
            int d_col = j + dc[d];
            // 判断d方向边界 || visted未访问过的  || 当前节点不是匹配的字符
            // 注意 边界d_row>row  当匹配到E元素是 这是 i，j =2   d=0  dr[d]=1 导致d_row=3 会导致board数组越界 因为board横坐标最大是2
            if (d_row < 0 || d_row >=row || d_col < 0 || d_col >=col ||
                    board[d_row][d_col] != word.charAt(pos) || visited[d_row][d_col] == 1) {
                continue;
            }
            // 位置合法字符、标记
            visited[d_row][d_col] = 1;
            // 一定满足符合的结果就返回
            if (dfs(board, word, d_row, d_col, row, col, pos + 1, visited)) {
                return true;
            }
            // 注意标记清除
            visited[d_row][d_col] = 0;
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        boolean result = isEqualWord(board, word);
        System.out.println(result);

    }


}
