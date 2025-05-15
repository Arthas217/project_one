package org.com.zlk.chxg.od;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。游戏参与者需要分多个回合顺序跳到第1格直到房子的最后一格。 跳房子的过程中，可以向前跳，也可以向后跳。
 * <p>
 * 假设房子的总格数是count，小红每回合可能连续跳的步数都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到最后一格？如果有，请输出索引和最小的步数组合。
 * <p>
 * 注意：数组中的步数可以重复，但数组中的元素不能重复使用。提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 * <p>
 * <p>
 * <p>
 * 输入描述：
 * 第一行输入为每个回合可能连续跳的步数，它是int整数数组类型。实际字符串中整数与逗号间可能存在空格。
 * 第二行输入为房子总格数count，它是int整数类型
 * <p>
 * 输出描述：
 * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）。
 * <p>
 * 补充说明
 * <p>
 * count<=1000, 0<steps.length<=5000, -100000000<=step[i]<100000000
 * <p>
 * <p>
 * <p>
 * 示例1
 * <p>
 * 输入
 * [1,4,5,2,2]
 * <p>
 * 7
 * <p>
 * 输出
 * [5,2]
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例2
 * <p>
 * 输入
 * [-1,2,4,9,6]
 * <p>
 * 8
 * <p>
 * 输出
 * [-1,9]
 * <p>
 * 说明
 * 此样例有多种组合满足两回合跳到最后，譬如：[-1,9]， [2,6], 其中[-1,9] 的索引和为0+3=3， [2,6]的索引和为1+4=5， 所以索引和最小的步数组合[-1,9]
 * <p>
 * 请帮我完成上面题目中的描述逻辑ACM模式单行多行输入输出，使用java代码实现以上题目的要求
 *
 * @author 会游泳的蚂蚁
 * @date 2025/5/15 15:50
 */
public class OD202505_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取第一行输入并处理为整数数组
        String line = scanner.nextLine().trim();
        line = line.substring(1, line.length() - 1); // 去除方括号
        String[] parts = line.split(",");
        int[] steps = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            steps[i] = Integer.parseInt(parts[i].trim());
        }

        // 读取第二行输入作为count
        int count = scanner.nextInt();

        // 寻找满足条件的步数组合
        int minIndexSum = Integer.MAX_VALUE;
        int[] result = new int[2];

        // 使用HashMap存储值和索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < steps.length; i++) {
            int complement = count - steps[i];
            if (map.containsKey(complement)) {
                int indexSum = map.get(complement) + i;
                if (indexSum < minIndexSum) {
                    minIndexSum = indexSum;
                    result[0] = steps[map.get(complement)];
                    result[1] = steps[i];
                }
            }
            // 只存储第一次出现的索引，保证索引和最小
            if (!map.containsKey(steps[i])) {
                map.put(steps[i], i);
            }
        }
        // 输出结果
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

}
