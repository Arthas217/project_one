package org.com.zlk.zcy;

/**
 * @Author zc217
 * @Date 2020/7/8
 */
public class zcyUtil {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
