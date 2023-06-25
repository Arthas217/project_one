package org.com.zlk.chxg.niuke.easy.str;

import java.util.Scanner;

/**
 * @author 会游泳的蚂蚁
 * @description: 字符串最后一个单词的长度
 * @date 2023/6/25 16:26
 */
public class HJ1 {

    /**
     * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
     * 输入描述：
     * 输入一行，代表要计算的字符串，非空，长度小于5000。
     * hello nowcoder
     * 输出描述：
     * 输出一个整数，表示输入字符串最后一个单词的长度。
     * 8
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
//        getLastWordLen(inputStr);
//        getLastWordLen2(inputStr);
        getLastWordLen3(inputStr);
    }

    /**
     * 系统函数
     * <p>
     * 运行时间
     * 42ms
     * 占用内存
     * 10904KB
     *
     * @param str
     */
    private static void getLastWordLen(String str) {
        int lastIndexOf = str.lastIndexOf(' ');
        System.out.println(str.length() - 1 - lastIndexOf);
    }

    /**
     * 系统函数
     * <p>
     * 运行时间
     * 42ms
     * 占用内存
     * 10700KB
     *
     * @param str
     */
    private static void getLastWordLen2(String str) {
        String[] s = str.split(" ");
        System.out.println(s[s.length - 1].length());
    }

    /**
     * 正则
     * <p>
     * 运行时间
     * 46ms
     * 占用内存
     * 10832KB
     *
     * @param str
     */
    private static void getLastWordLen22(String str) {
        String[] s = str.split("\\s+");
        System.out.println(s[s.length - 1].length());
    }


    /**
     * 从字符串最后开始遍历
     *
     * @param inputStr
     */
    private static void getLastWordLen3(String inputStr) {
        int count = 0;
        for (int i = inputStr.length() - 1; i > 0; i--) {
            if (inputStr.charAt(i) == ' ') {
                break;
            }
            count++;
        }
        System.out.println(count);

    }
}
