package org.com.zlk.java8.zzzz.zero;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:25
 */
public interface IDonkey {
    default void run() {
        System.out.println("IDonkey run");
    }
}
