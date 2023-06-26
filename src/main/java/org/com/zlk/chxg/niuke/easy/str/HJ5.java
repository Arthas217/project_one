package org.com.zlk.chxg.niuke.easy.str;

import java.util.Scanner;

/**
 * @author 会游泳的蚂蚁
 * @description: 进制转换
 * @date 2023/6/26 11:50
 */
public class HJ5 {

    /**
     * 接受一个十六进制的数，输出该数值的十进制表示。
     * 1≤n≤2的31次方
     * <p>
     * 输入描述：
     * 输入一个十六进制的数值字符串。
     * <p>
     * 输出描述：
     * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        transTen(input);
    }

    /**
     * oxAA （A的ASCII码数值是65-90，a的ASCII码数值是97-122  0-9 ASCII码数值48到57)
     * 运行时间：43ms 占用内存：10944KB
     * @param input
     */
    private static void transTen(String input) {
        //略过前2位
        String substring = input.substring(2);
        int len = input.substring(2).length();
        int sum = 0;
        //从右到左
        for (int i = len - 1; i >= 0; i--) {
            char c = substring.charAt(i);
            int temp = c;
            //大写字母
            if (temp >= 65) {
                temp = temp - 65 + 10;
            } else {
                //数字
                temp = temp - 48;
            }
            sum = sum + (int) Math.pow(16, len - i - 1) * temp;
        }
        System.out.println(sum);
    }
}
