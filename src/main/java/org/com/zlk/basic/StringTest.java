package org.com.zlk.basic;

/**
 * https://www.cnblogs.com/yso1983/archive/2009/12/07/1618564.html
 */
public class StringTest {
    public static void main(String[] args) {
        byte[] b = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String sb = new String(b);                 //abcdefghij
        String sb_sub = new String(b, 3, 2);     //de
        String sc = new String(c);                  //0123456789
        String sc_sub = new String(c, 3, 2);    //34
        String sb_copy = new String(sb);       //abcdefghij
        System.out.println("sb:" + sb);
        System.out.println("sb_sub:" + sb_sub);
        System.out.println("sc:" + sc);
        System.out.println("sc_sub:" + sc_sub);
        System.out.println("sb_copy:" + sb_copy);
    }

    private static void method() {
        // 字符串数组赋值
        String[] s1 = new String[]{};
        String[] s2 = new String[]{"a", "b"};
        String[] s3 = new String[2];
        System.out.println(s1.length);
        for (String ss : s1) {
            System.out.println(ss);
        }
        for (String ss : s2) {
            System.out.println(ss);
        }
        for (String ss : s3) {
            System.out.println(ss);
        }
    }
}
