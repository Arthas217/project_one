package org.com.zlk.company.toutiao;

/**
 * 求有序数组里重复元素的个数
 *
 * @Author zc217
 * @Date 2020/7/10
 */
public class ArrayCommonElementNum {

    /**
     * 先用二分查找到target的index，然后在从index分别向两边查找
     */
    public static int acen(int[] arr, int target) {
        int index = binarySearch(arr, target);
        int count = commonNum(arr, index, target);
        return count;
    }

    public static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length;
        for (int i = 0; i < end; i++) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    public static int commonNum(int[] arr, int index, int target) {
        int sum = 0;
        // 从index向右查找
        for (int i = index; i + 1 < arr.length; i++) {
            if (arr[i] == arr[i + 1] && arr[i] == target) {
                sum++;
            }
        }
        // 从index向左查找
        for (int i = index; i >= 1; i--) {
            if (arr[i] == arr[i - 1] && arr[i] == target) {
                sum++;
            }
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 8, 10, 12};
        int target = 4;
        System.out.println(acen(arr, target));
    }
}
