package org.com.zlk.company.toutiao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个数之和
 * 哈希存储 kv(值，下标)
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] n = {2, 7, 11, 15};
        int[] result = twoSum(n, 9);
        Arrays.stream(result).forEach(System.out::println);
    }

}
