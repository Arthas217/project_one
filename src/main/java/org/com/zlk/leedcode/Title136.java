package org.com.zlk.leedcode;

import java.util.HashMap;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
 */
public class Title136 {

    public static void main(String[] args) {

        int[] nums = {4, 1, 2, 1, 2, 4, 3, 5, 3};

        System.out.println(singleNumberOfHashtable(nums));

        System.out.println(getSingleNumOfxor(nums));

    }

    /**
     * 哈希表（key=元素值 value=元素出现次数）
     */
    private static int singleNumberOfHashtable(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap();
        for (int n : nums) {
            hashMap.put(n, hashMap.getOrDefault(n, 0) + 1);
        }
        for (int m : nums) {
            if (1 == hashMap.get(m)) {
                return m;
            }
        }
        return 0;
    }

    /**
     * 每个元素进行异或运算。 相同为0，不同为1
     */
    private static int getSingleNumOfxor(int[] nums) {
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        return xor;
    }
}
