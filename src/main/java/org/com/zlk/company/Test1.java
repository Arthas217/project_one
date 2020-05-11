package org.com.zlk.company;

/**
 * 等腰三角形
 */
public class Test1 {

    public static void main(String[] args) {
        printValue(5);
    }

    private static void printValue(int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num - i - 1; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i + 1; k++) {
                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
