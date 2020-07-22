package org.com.zlk.leedcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * ∼x    位运算 NOT
 * x & y 位运算 AND
 * x ⊕ y 位运算 XOR
 * |是按位或
 * ^是按位异或
 * &是按位与
 * << 表示左移，不分正负数，低位补0
 * >> 表示右移，如果该数为正，则高位补0，若为负数，则高位补1
 * >>> 无符号右移一位,右移一位不足的补0
 *
 * @Author zc217
 * @Date 2020/7/22
 */
public class ArraySolution {

    /**
     * 136. 只出现一次的数字  (一个非空整数数组,其余每个元素均出现两次)
     */
    public static int singleNumber(int[] arr) {
        // 每个元素进行异或运算  a⊕0=a  a⊕a=0
        int xor = 0;
        for (int e : arr) {
            xor ^= e;
        }
        return xor;
    }

    /**
     * 136. 只出现一次的数字 哈希表（key=元素值 value=元素出现次数）
     */
    public static int singleNumberOfHashtable(int[] arr) {
        Map<Integer, Integer> map = new HashMap();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for (int m : arr) {
            if (1 == map.get(m)) {
                return m;
            }
        }
        return -1;
    }

    // 137. 只出现一次的数字 II (一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次)
    // https://leetcode-cn.com/problems/single-number-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
    // 统计所有数字的各二进制位中 11 的出现次数，并对 33 求余，结果则为只出现一次的数字。
    public static int singleNumber2(int[] nums) {
        int res = 0;
        //从第0位、最低位开始(整形4字节）
        for (int i = 0; i < 32; i++) {
            // 记录每一位出现的次数
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                //遍历数组中每一个数的当前i位是否是1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //count个数是否是3的倍数
            if (count % 3 != 0) {
                // 1 << i 可理解为2的i次幂的值
                res = res | 1 << i;
            }
        }
        return res;
    }

    // 260. 只出现一次的数字 III(一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次)
    // 输出的顺序并不重要 具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
    public static int[] singleNumber3(int[] nums) {
        HashSet<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        int[] result = new int[2];
        int i = 0;
        for (int n : set) {
            result[i] = n;
            i++;
        }
        return result;
    }

    // 260. 只出现一次的数字 III 技巧性
    // https://leetcode-cn.com/problems/single-number-iii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-8/
    public int[] singleNumber33(int[] nums) {
        int diff = 0;
        // 所有的数字异或结果 3 ^ 5 = 6 (110)
        for (int n : nums) {
            diff ^= n;
        }
        // 保留某个数的最高位的 1
        diff = Integer.highestOneBit(diff);
        int[] result = { 0, 0 };
        // 将原数组分成两组,组内分别进行异或,两组分别异或的结果。
        for (int n : nums) {
            //当前位是 0 的组   (1,2,1,3,2)
            if ((diff & n) == 0) {
                result[0] ^= n;

            } else {
                //当前位是 1 的组(5)
                result[1] ^= n;
            }
        }
        return result;
    }



    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2, 4, 3, 7, 3};
        int[] arr2 = {1, 2, -6, 1, 1, 2, 2, 3, 3, 3};
        int[] arr3 = {1, 2, 1, 3, 2, 5};
//        System.out.println(singleNumber(arr));
//        System.out.println(singleNumberOfHashtable(arr));
//        System.out.println(singleNumber2(arr2));
        int[] res = singleNumber3(arr3);
        for (int n: res) {
            System.out.println(n);
        }
    }
}
