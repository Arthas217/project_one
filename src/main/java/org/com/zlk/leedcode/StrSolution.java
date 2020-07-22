package org.com.zlk.leedcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zc217
 * @Date 2020/7/22
 */
public class StrSolution {

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

    // 125. 验证回文串 递归实现 时间复杂度：O(s) 空间复杂度O(1)
    public static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if(left >= right){
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


    public static void main(String[] args) {
//        String str = "abcbcbb";
//        System.out.println(lengthOfLongestSubstring(str));

//        String st = "A man, a plan, a canal: Panama";
//        System.out.println(isPalindrome(st));

        String str = "amanaplanacanalpanama";
        System.out.println(longestPalindrome(str));
        System.out.println(longestPalindrome2(str));
    }
}
