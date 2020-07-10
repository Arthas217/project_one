package org.com.zlk.company.toutiao;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复的最长子串
 */
public class LongStrNoDuplacate {

    public static String str = "abcabcbb";

    public static void main(String[] args) {
        System.out.println(getLongSND(str));
    }

    private static int getLongSND(String str) {
        int len = str.length();
        int windowValue = 0;
        int max = 0;
        int index = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 滑动窗口机制 windowValue保持最大窗口值
        for (int end = 0, start = 0; end < len; end++) {
            char c = str.charAt(end);
            // 重复元素
            if (map.containsKey(c)) {
                // 移动start位置
                start = Math.max(map.get(c), start);
            }
            map.put(str.charAt(end), end + 1);
            windowValue = Math.max(end - start + 1, windowValue);

            // 记录windowValue最大值和位置
            if (windowValue > max) {
                max = windowValue;
                index = end;
            }
        }
        System.out.println(str.substring(index - max + 1, index + 1));
        return windowValue;
    }
}
