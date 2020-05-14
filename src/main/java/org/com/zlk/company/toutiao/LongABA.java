package org.com.zlk.company.toutiao;

/**
 * 最长回文子串
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 */
public class LongABA {
    // TODO

    /**
     * 暴力解法
     */
    public static String lowestMethod(String str) {
        int max = 0;
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String sub = str.substring(i, j);
                if (isPalindromic(sub) && sub.length() > max) {
                    result = str.substring(i, j);
                    max = Math.max(result.length(), max);
                }
            }
        }
        return result;
    }

    /**
     * 是否是回文
     */
    public static boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String result = lowestMethod("abakflaacaad");
        System.out.println(result);
    }

}
