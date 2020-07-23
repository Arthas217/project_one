package org.com.zlk.leedcode.offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 由于每次替换从 1 个字符变成 3 个字符，使用字符数组可方便地进行替换。建立字符数组地长度为 s 的长度的 3 倍，这样可保证字符数组可以容纳所有替换后的字符。
 */
public class BlankReplace {

    public static void main(String[] args) {

        String s = "abc de ";
        System.out.println(replaceSpace(s));

    }

    private static String replaceSpace(String s) {
        int len = s.length();
        char[] chars = new char[len * 3];
        int size = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = c;
            }
        }
        // size 记录真实的位置
        String result = new String(chars, 0, size);
        return result;
    }
}
