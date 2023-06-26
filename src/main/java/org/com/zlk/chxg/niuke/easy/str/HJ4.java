package org.com.zlk.chxg.niuke.easy.str;

import java.util.Scanner;

/**
 * @author 会游泳的蚂蚁
 * @description: 字符串分隔
 * @date 2023/6/25 19:09
 */
public class HJ4 {

    /**
     * 输入一个字符串,连续输入字符串(每个字符串长度小于等于100)，请按长度为8拆分每个输入字符串并进行输出；
     * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        print8Str(input);
    }

    /**
     * 运行时间
     * 42ms
     * 占用内存
     * 10968KB
     * @param input
     */
    private static void print8Str(String input) {
        if (input.length() <= 0) {
            return;
        }
        int count = input.length() / 8;
        int left = input.length() % 8;
        int countLoc = 0;
        for (int i = 0; i < count; i++) {
            System.out.println(input.substring(i * 8, (i + 1) * 8));
            countLoc = (i + 1) * 8;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (left != 0) {
            int zeroCount = 8 - left;
            for (int i = 0; i < zeroCount; i++) {
                stringBuilder.append("0");
            }
        }
        System.out.println(input.substring(countLoc)+stringBuilder);
    }

}
