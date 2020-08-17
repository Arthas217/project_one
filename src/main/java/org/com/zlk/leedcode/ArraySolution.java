package org.com.zlk.leedcode;

import java.util.*;

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

    // company2个有序数组，找出其中的交集
    public static int[] retainAll(int[] array1, int[] array2) {
        List<Integer> res = new ArrayList();
        if (array1.length < 1 || array2.length < 1) {
            return res.stream().mapToInt(Integer::intValue).toArray();
        }
        int i = 0, j = 0;
        while (i < array1.length && j < array1.length) {
            if (array1[i] == array2[j]) {
                res.add(array1[i]);
                i++;
                j++;
            } else if (array1[i] > array2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    // 两个字符数组中，除了一个字符不同，其他字符的值和顺序都相同（假设char1字符数组长度长，且不同的那个字符在char2中）
    public static char find(char[] char1, char[] char2) {
        int index = char1.length;
        for (int i = char2.length - 1; i >= 0; i--) {
            index = bs(char1, char2[i], index - 1);
            if (index < 0 || index > char1.length - 1) {
                return char2[i];
            }
            if (char2[i] == char1[index]) {
                continue;
            }
        }
        return ' ';
    }

    public static int bs(char[] chars, char c, int index) {
        if (index < 0) {
            return -1;
        }
        int l = 0, r = index;
        while (l <= r) {
            int m = (l + r) / 2;
            if (chars[m] == c) {
                return m;
            } else if (chars[m] > c) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    public static char find2(char[] char1, char[] char2) {
        int index1 = bs2(char1, char2);
        return char2[index1];
    }

    public static int bs2(char[] chars1, char[] chars2) {
        int l1 = 0, r1 = chars1.length - 1;
        int l2 = 0, r2 = chars2.length - 1;
        int n = 0;
        int m = 0;
        while (l1 <= r1 && l2 <= r2) {
            m = (l1 + r1) / 2;
            n = (l2 + r2) / 2;
            if (chars1[m] < chars2[n]) {
                l1 = m + 1;
            } else if (chars1[m] > chars2[n]) {
                r1 = m - 1;
            } else if (chars1[m] == chars2[n] && chars2[r2] > chars1[m]) {
                l1 = m + 1;
                l2 = n + 1;
            } else if (chars1[m] == chars2[n] && chars2[r2] ==chars1[m]) {
                r1 = m - 1;
                r2 = n - 1;
            }
        }
        return n;
    }


    /**
     * 1323. 6 和 9 组成的最大数字
     */
    public static int maximum69Number(int num) {
        int index = getIndex(num);
        int wei;
        int remain = -1;
        int[] arr = new int[index];
        int minIndex = index;
        while (remain != 0) {
            wei = num % 10;
            remain = num / 10;
            if (wei == 6 && index < minIndex) {
                minIndex = index;
            }
            arr[--index] = wei;
            num = remain;
        }
        if (minIndex != index) {
            arr[--minIndex] = 9;
        }
        if (remain == 6) {
            arr[index] = 9;
        }
        return getResult(arr);
    }

    private static int getIndex(int num) {
        int cap = 1;
        int re = num;
        while (re > 10) {
            re = num / 10;
            cap++;
            num = re;
        }
        return cap;
    }

    private static int getResult(int[] arr) {
        int length = arr.length;
        int total = 0;
        for (int i = 0; i < length; i++) {
            int num = arr[i];
            total = total + calculateWeightNum(num, length - (i + 1));
        }
        return total;
    }

    private static int calculateWeightNum(int num, int numOfTen) {
        int result = num;
        for (int i = 0; numOfTen > 0 && i < numOfTen; i++) {
            result = result * 10;
        }
        return result;
    }

    public static int maximum69Number2(int num) {
        String str = String.valueOf(num);
        String newStr = str.replaceFirst("6", "9");
        return Integer.valueOf(newStr);
    }


    /**
     * 1491. 去掉最低工资和最高工资后的工资平均值
     */
    public static double average(int[] salary) {
        int len = salary.length;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        double sum = 0;
        for (int n : salary) {
            max = Math.max(n, max);
            min = Math.min(n, min);
            sum += n;
        }
        return (sum - max - min) / (len - 2);
    }


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
        int[] result = {0, 0};
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


    //  小米面试：数组(类似队列）取和最大的k个连续元素
    public static int maxSumQueue4K(int[] arr, int k) {
        int max = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += arr[(i + j) % len];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    // 普通版：数组中取最大的k个连续元素的和
    public static int maxSumArray4K(int[] arr, int k) {
        int max = 0;
        int len = arr.length;
        for (int i = 0; i < len - k + 1; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += arr[i + j];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    // 1. 两数之和
    public static int[] twoSum(int[] nums, int target) {
        // key:num[i]元素值  value:i元素的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = target - nums[i];
            if (map.containsKey(key)) {
                return new int[]{map.get(key), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    // 4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0d;
    }

}
