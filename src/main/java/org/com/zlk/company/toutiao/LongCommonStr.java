package org.com.zlk.company.toutiao;

/**
 * 最长公共子串
 */
public class LongCommonStr {

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "bebcd";

        String result = lcstr1(str1, str2);
        String perfect = lcstr2(str1, str2);
        System.out.println(result);
        System.out.println(perfect);
    }

    /**
     * 空间复杂度O（1）
     * 斜线上len长度最大累计max值，及位置end
     */
    private static String lcstr2(String str1, String str2) {
        if (str1 == null || str2 == null || str1.equals("") || str2.equals("")) {
            return "";
        }
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        // 矩阵右上角位置算起
        int row = 0; // 斜线开始位置的行
        int col = char2.length - 1;  // 斜线开始位置的列

        int max = 0; // 记录最大长度
        int end = 0; // 记录最大长度更新时，子串结尾的位置
        // 判断终止条件是行的边界
        while (row < char1.length) {
            int i = row;
            int j = col;
            //斜线上计算每个位置的值
            int len = 0;
            // 从（i，j)位置 向右下角方向遍历
            while (i < char1.length && j < char2.length) {
                if (char1[i] != char2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    end = i;
                    max = len;
                }
                i++;
                j++;
            }
            // 每条斜线开始的位置 （从右往左移动，从上往下移动）
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return str1.substring(end - max + 1, end + 1);
    }

    /**
     * 经典动态规划  时间复杂度（i*j)  空间复杂度(i*j)
     */
    private static String lcstr1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) {
            return "";
        }
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        // 获取dp矩阵值
        int[][] dp = getDp(char1, char2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        // dp[3][4] =3  即s1[1...3]
        return s1.substring(end - max + 1, end + 1);
    }

    /**
     * 获取二维矩阵值
     * 空间复杂度是O(i*j)
     */
    private static int[][] getDp(char[] char1, char[] char2) {
        int[][] dp = new int[char1.length][char2.length];

        //第一列赋值
        for (int i = 0; i < char1.length; i++) {
            if (char1[i] == char2[0]) {
                dp[i][0] = 1;
            }
        }
        //第一行赋值
        for (int j = 1; j < char2.length; j++) {
            if (char1[0] == char2[j]) {
                dp[0][j] = 1;
            }
        }
        // 其他元素赋值
        // 如果两个字符不相同 赋值=0
        // 如果两个字符相同  赋值=左上角值+1
        for (int i = 1; i < char1.length; i++) {
            for (int j = 1; j < char2.length; j++) {
                if (char1[i] == char2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }
}
