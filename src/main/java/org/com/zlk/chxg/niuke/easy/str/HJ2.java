package org.com.zlk.chxg.niuke.easy.str;

import java.util.Scanner;

/**
 * @author 会游泳的蚂蚁
 * @description: 计算某字符出现次数
 * 数据范围【1,1000】
 * @date 2023/6/25 17:59
 */
public class HJ2 {

    /**
     * 一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
     * 输入描述：
     * 第一行输入一个由字母、数字和空格组成的字符串，第二行输入一个字符（保证该字符不为空格）。
     * ABCabc1d bdD
     * b
     * 输出描述：
     * 输出输入字符串中含有该字符的个数。（不区分大小写字母）
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String matchStr = scanner.nextLine();
        countNumSameChar(str, matchStr);

    }

    private static void countNumSameChar(String str, String matchStr) {
        String upperCase = str.toUpperCase();
        String match = matchStr.toUpperCase();
        int count = 0;
        for (int i = 0; i < upperCase.length(); i++) {
            if (upperCase.charAt(i) == match.charAt(0)) {
                count++;
            }
        }
        System.out.println(count);
    }
}
