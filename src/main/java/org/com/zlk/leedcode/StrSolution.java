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
        // 记录窗口最大值时，窗口右边界的位置
        int windowIndex = 0;
        // 最大值
        int max = 0;
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
            if (windowMax > max) {
                max = windowMax;
                windowIndex = end;
            }
        }
        System.out.println(s.substring(windowIndex - max + 1, windowIndex + 1));
        return windowMax;
    }

    public static void main(String[] args) {
        String str = "abcbcbb";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
