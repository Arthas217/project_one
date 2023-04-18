package org.com.zlk.chxg.basic;

/**
 * @Author 会游泳的蚂蚁
 * @Description: while循环
 * @Date 2022/9/5 15:02
 */
public class whileDemo {

    public static void main(String[] args) {
        while_use();
    }

    /**
     * while 是先判断后执行，do while是先执行在判断，do while至少会执行一次
     */
    public static void while_use() {
        int i = 0;
        do {
            i++;
        } while (i < 10);
        System.out.println(i);
        System.out.println("===============");

        while (i < 0) {
            System.out.println(i);
            i++;
        }
        System.out.println("+++++++++++++++");//do while 执行一次

        do {
            System.out.println(i);
            i--;
        } while (i < 0);
        System.out.println(i);
    }

}
