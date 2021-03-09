package org.com.zlk.datastructure;

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

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     */
    public static int[] exchangeArrayElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // 当找到一个偶数时，就跳出循环。
            while (left < right && (arr[left] & 1) == 1) {
                left++;
            }
            // 当找到一个奇数时，就跳出循环
            while (left < right && (arr[right] & 1) == 0) {
                right--;
            }
            // 如果两个指针还没有碰到一起时，说明找到了需要交换的位置
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        return arr;
    }

    /**
     * 2个有序数组，找出其中的交集
     */
    public static int[] retainAll(int[] arr1, int[] arr2) {
        List<Integer> res = new ArrayList();
        if (arr1.length < 1 || arr2.length < 1) {
            return res.stream().mapToInt(Integer::intValue).toArray();
        }
        int i = 0, j = 0;
        while (i < arr1.length && j < arr1.length) {
            if (arr1[i] == arr2[j]) {
                res.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 两个字符数组中，除了一个字符不同，其他字符的值和顺序都相同（假设char1字符数组长度长，且不同的那个字符在char2中）
     */
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
            } else if (chars1[m] == chars2[n] && chars2[r2] == chars1[m]) {
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
        int num = 0;
        for (int e : arr) {
            num ^= e;
        }
        return num;
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
    public static int[] singleNumber3(int[] arr) {
        HashSet<Integer> set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                set.remove(arr[i]);
            } else {
                set.add(arr[i]);
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


    //  小米面试：数组类似环操作，取和最大的k个连续元素
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

    // 数组中取最大的k个连续元素的和
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

    // 4. 寻找两个正序数组的中位数（数组长度不大于1000  -10的6次幂 <= nums1[i], nums2[i] <= 10的6次幂）
    // 数组合并（归并）时间复杂度O(m+n)，空间复杂度O(m+n)
    // 如果总长度为奇数的话，那么合并后中间的那个数就是结果;如果总长度为偶数的话，那合并后中间两个数的平均数就是结果
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        double result = 0;
        if (length % 2 != 0) {
            return getNum(nums1, nums2, length / 2);
        } else {
            result = getNum(nums1, nums2, length / 2 - 1) / 2 + getNum(nums1, nums2, length / 2) / 2;
        }
        return result;
    }

    // 数组归并到中位数的位置就可以
    private double getNum(int[] nums1, int[] nums2, int k) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0;
        int cur = 0;
        while (i < nums1.length && j < nums2.length && cur <= k) {
            if (nums1[i] < nums2[j]) {
                result[cur++] = nums1[i++];
            } else {
                result[cur++] = nums2[j++];
            }
        }
        while (i < nums1.length && cur <= k) {
            result[cur++] = nums1[i++];
        }
        while (j < nums2.length && cur <= k) {
            result[cur++] = nums2[j++];
        }
        return result[cur - 1];

    }
    // 4. 寻找两个正序数组的中位数  二分查找
    // 参考https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/4-xun-zhao-liang-ge-zheng-xu-shu-zu-de-zhong-we-15/


    // 找1到n中缺失的数字(给定一个长度为n-1的整形数组，数字的范围在1到n（无重复），其中有一个缺失的数字) https://blog.csdn.net/qq_24336773/article/details/81709343
    public static int getLose(int[] a, int n) {
        int t = 0;
        // 异或运算
        for (int i = 1; i <= n; i++) {
            t = t ^ i;
        }
        for (int i = 0; i < n - 1; i++) {
            t = t ^ a[i];
        }
        return t;
    }



    /**
     * 两个升序数组
     * {1,2,5,7,9}
     * {2,2,3,4,6}
     * 合并数组输出第偶数个值到新数组。
     */
    public static int[] printEvenLocationOfMergeArray(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int p1 = 0, p2 = 0;
        //新数组大小
        int[] nums = new int[(m + n) / 2];
        //偶数位置下标
        int location = 1;
        // 新数组元素下标
        int index = 0;
        while (p1 < m && p2 < n) {
            if (arr1[p1] < arr2[p2]) {
                if (location % 2 == 0) {
                    nums[index++] = arr1[p1];
                }
                p1++;
            } else {
                if (location % 2 == 0) {
                    nums[index++] = arr2[p2];
                }
                p2++;
            }
            location++;
        }
        while (p1 < m) {
            if (location % 2 == 0) {
                nums[index++] = arr1[p1];
            }
            p1++;
            location++;
        }
        while (p2 < n) {
            if (location % 2 == 0) {
                nums[index++] = arr2[p2];
            }
            p2++;
            location++;
        }
        return nums;
    }


    /**
     * O(N) 给定两个排好序的数组，怎样高效地判断这两个数组中是否存在相同的数字
     */
    public static boolean findCommonNum4Array(int[] a, int[] b) {
        boolean res = false;
        int sizeA = a.length;
        int sizeB = b.length;
        if (sizeA <= 0 || sizeB <= 0) {
            return res;
        }
        int i = 0, j = 0;
        while (i < sizeA && j < sizeB) {
            if (a[i] == b[j]) {
                res = true;
                break;
            }
            if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }


    //合并数组[4,1,3,9,6,2]和[8,5,3,2,1,4,7]，然后去重，取出偶数并倒排输出（可使用容器）
    public static int[] distinctAndSort(int[] arr1, int[] arr2) {
        TreeSet<Integer> set = new TreeSet(new MyCompare());
        for (int i : arr1) {
            set.add(i);
        }
        for (int i : arr2) {
            set.add(i);
        }
        Iterator<Integer> it = set.iterator();
        int[] arr = new int[set.size() / 2];
        int i = 0;
        while (it.hasNext()) {
            Integer value = it.next();
            if (value % 2 == 0) {
                arr[i++] = value;
            }
        }
        return arr;
    }

    static class MyCompare implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    // 两个长度为N的数组A，B，已分别按升序排列，求第N/第N+1个数（要求时间复杂度尽可能低）
    // https://www.cnblogs.com/diegodu/p/4589847.html
    public static int getN(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return -1;
        }
        if (arr1.length == 0 && arr2.length == 0) {
            return -1;
        }
        return helpGetN(arr1, 0, arr1.length - 1, arr2, 0, arr1.length - 1);
    }

    private static int helpGetN(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
        if (start1 == end1) {
            return Math.min(arr1[start1], arr2[start2]);
        }
        int size = end1 - start1 + 1;
        int halfSize;
        if (size % 2 != 0) {
            halfSize = (size + 1) / 2;
        } else {
            halfSize = size / 2;
        }
        if (arr1[start1 + halfSize - 1] == arr2[start2 + halfSize - 1]) {
            return arr1[start1 + halfSize - 1];
        } else if (arr1[start1 + halfSize - 1] > arr2[start2 + halfSize - 1]) {
            return helpGetN(arr1, start1, start1 + halfSize - 1, arr2, end2 - (halfSize - 1), end2);
        } else {
            return helpGetN(arr1, end1 - (halfSize - 1), end1, arr2, start2, start2 + halfSize - 1);
        }
    }

}
