package org.com.zlk.basic;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/9/5 15:02
 */
public class XunHuan {

    /**
     * while 是先判断后执行，do while是先执行在判断，do while至少会执行一次
     */
    public static void doWhile() {
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
            i++;
            if (i == 11) {
                return;
            }
        } while (i < 0);
        System.out.println(i);
    }

    public static void main(String[] args) {
        doWhile();
    }
}
