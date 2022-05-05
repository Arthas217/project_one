package org.com.zlk.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author zc217
 * @Date 2020/7/22
 */
public class StrSolution {

    // 剑指 Offer 05. 替换空格
    public static String replaceSpace(String s) {
        int len = s.length();
        char[] chars = new char[len * 3];
        int size = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = c;
            }
        }
        // size 记录真实的位置
        return new String(chars, 0, size);
    }

    // 3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口最大值
        int windowMax = 0;
//        // 记录窗口最大值时，窗口右边界的位置
//        int windowIndex = 0;
//        // 最大值
//        int max = 0;
        // key值：字符，value值：字符位置+1时开始不重复 用于更新start的判断
        Map<Character, Integer> map = new HashMap<>();
        // 滑动窗口机制
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // 包含重复字符
            if (map.containsKey(c)) {
                // 移动start位置
                start = Math.max(map.get(c), start);
            }
            windowMax = Math.max(end - start + 1, windowMax);
            map.put(s.charAt(end), end + 1);
            // 记录windowValue保持最大窗口值和位置
//            if (windowMax > max) {
//                max = windowMax;
//                windowIndex = end;
//            }
        }
//        System.out.println(s.substring(windowIndex - max + 1, windowIndex + 1));
        return windowMax;
    }

    // 125. 验证回文串 递归实现
    public static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        // 非字母和数字移动跳过
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
            left++;
        }
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
            right--;
        }
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        // 注意 ++left  否则会栈溢出
        return isPalindromeHelper(s, ++left, --right);
    }

    // 5. 最长回文子串 暴力
    public static String longestPalindrome(String s) {
        int longest = 0;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                // 左闭右开区间
                String sub = s.substring(i, j);
                if (isPalindrome(sub) && sub.length() > longest) {
                    result = s.substring(i, j);
                    longest = Math.max(result.length(), longest);
                }
            }
        }
        return result;
    }

    // 5. 最长回文子串 动态规划
    // https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
    public static String longestPalindrome2(String s) {
        int len = s.length();
        // 单字符 || null
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否为回文子串 这里都是闭区间,i <= j ，因此，只需要填这张表格对角线以上的部分
        boolean[][] dp = new boolean[len][len];
        char[] ch = s.toCharArray();
        // dp对角线上表示只有一个字符
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        // 公式 dp[i][j] = (s[i] == s[j]) 头尾字符是否相等 && dp[i + 1][j - 1]
        for (int j = 1; j < len; j++) {
            //dp的每一行填值 （true、false）
            for (int i = 0; i < j; i++) {
                if (ch[i] != ch[j]) {
                    dp[i][j] = false;
                } else {
                    // dp[i + 1][j - 1] 考虑边界情况,当不构成区间时，即j - 1 - (i + 1) + 1 < 2  即j - i  < 3 。
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 选择满足dp值=true的最大len并把位置记录下来
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    // 腾讯 压缩字符串的解压
    // 递归方法 https://www.jianshu.com/p/001b49c2bdcd
    public static String tar(String s) {
        // 递归结束的判断，说明全部解压完毕
        if (!s.contains("[") && !s.contains("|")) {
            return s;
        }
        // 递归到最里层 形如2|cd的变成cdcd
        if (!s.contains("[") && s.contains("|")) {
            String x[] = s.split("\\|");
            int num = Integer.parseInt(x[0]);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < num; i++){
                sb.append(x[1]);}
            return sb.toString();
        }
        // 上面if都不执行，说明既有[又有|，说明没有递归到最里层
        char a[] = s.toCharArray();
        // 用来存储完全解压后的字符串
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            // 设置栅栏，使得"["与"]"的个数相同，比如HG[3|B[2|CA]]F,会取得[3|B[2|CA]]
            int latch = 0;
            if (a[i] == '[') {
                latch++;
                // 指针往前进一位，形如[3|B[2|CA]]，需要得到3|B[2|CA],为了去掉最外面的括号
                i++;
                if (a[i] == ']') {
                    latch--;
                }
                // 用来存储部分解压的字符串，比如有字符串HG[3|B[2|CA]]F中的,这次while循环结束 s1会变成3|B[2|CA]
                // 这里再次进行'['的判断是存在[[]]的情况
                StringBuffer s1 = new StringBuffer();
                while (!(a[i] == ']' && latch == 0)) {
                    if (a[i] == '[') {
                        latch++;
                    }
                    if (a[i] == ']') {
                        latch--;
                        if (latch == 0) {
                            // 说明到了最外层的]了，不进行下面的appen，为了取出最外层的[]
                            continue;
                        }

                    }
                    s1.append(a[i]);
                    // 指针后移，再次进入while循环
                    i++;

                }
                // 如果有初始字符串HG[3|B[2|CA]]F，此时s1为3|B[2|CA]，去除了一层括号，
                String s2 = tar(s1.toString());
                // 判断里面还有没有未解压的字符串，有就继续解压，会递归到最里面的2|CA，得到CACA，返回到s2=3|BCACA,再次进行解压
                while (s2.contains("|")) {
                    s2 = tar(s2);
                }
                // 将解压完毕的字符串字符串加到sb后面
                sb.append(s2);
            } else {
                // 如果没有进行压缩的字符串，直接加到末尾就行
                sb.append(a[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(tar(s));
    }


}
