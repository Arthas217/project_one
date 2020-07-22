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

    public static void main(String[] args) {
//        String str = "abcbcbb";
//        System.out.println(lengthOfLongestSubstring(str));

//        String st = "A man, a plan, a canal: Panama";
//        System.out.println(isPalindrome(st));

        String str = "babad";
        System.out.println(longestPalindrome(str));
    }
}
