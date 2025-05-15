package org.com.zlk.chxg.od;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 项目组共有N个开发人员，项目经理接到了M个独立的需求，每个需求的工作量不同，且每个需求只能由一个开发人员独立完成，不能多人合作。假定各个需求直接无任何先后依赖关系，请设计算法帮助项目经理进行工作安排，使整个项目能用最少的时间交付。
 *
 * 输入描述：
 *   第一行输入为M个需求的工作量，单位为天，用逗号隔开。例如X1 X2 X3 …, Xm  表示共有M个需求，每个需求的工作量为X1天，X2天.....Xm天。其中0<M<30, 0<Xm<200
 *
 *   第二行输入为项目组人员数量N，例如 5 ，表示共有5名员工，其中0<N<10
 *
 * 输出描述：
 *   最快完成所有工作的天数。 例如： 25 表示最短需要25天能完成所有工作
 *
 *
 *
 * 示例1
 *
 * 输入
 * 6 2 7 7 9 3 2 1 3 11 4
 *
 * 2
 *
 * 输出
 * 28
 *
 * 说明
 * 共有二位员工，其中一位分配需求6 2 7 7 3 2 1共需要28天完成，另一位分配需求9  3 11  4 共需要27天完成，故完成所有工作至少需要28天。
 *
 *
 *
 * java编程要求： 时间限制5秒， 空间限制32MB，
 *
 * 请帮我完成上面题目中的描述逻辑ACM模式单行多行输入输出，使用java代码实现以上题目的要求
 *
 * @author 会游泳的蚂蚁
 * @date 2025/5/15 15:50
 */
public class OD202505_3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取第一行输入：需求工作量
        String[] workloadsStr = scanner.nextLine().split(" ");
        int[] workloads = new int[workloadsStr.length];
        for (int i = 0; i < workloadsStr.length; i++) {
            workloads[i] = Integer.parseInt(workloadsStr[i]);
        }
        // 对工作量进行降序排序
        Arrays.sort(workloads);
        reverse(workloads);
        // 读取第二行输入：开发人员数量
        int n = scanner.nextInt();
        // 调用算法计算最短完成时间
        int result = minCompletionTime(workloads, n);
        System.out.println(result);
    }

    // 反转数组
    private static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 使用二分查找和贪心算法计算最短完成时间
    private static int minCompletionTime(int[] workloads, int n) {
        // 确定二分查找的左右边界
        int left = Arrays.stream(workloads).max().getAsInt();
        int right = Arrays.stream(workloads).sum();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canComplete(workloads, n, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 判断在给定天数内是否能完成所有工作
    private static boolean canComplete(int[] workloads, int n, int days) {
        int workers = 1;
        int currentLoad = 0;
        for (int workload : workloads) {
            if (currentLoad + workload <= days) {
                currentLoad += workload;
            } else {
                workers++;
                currentLoad = workload;
                if (workers > n) {
                    return false;
                }
            }
        }
        return true;
    }

}
