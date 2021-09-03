package org.com.zlk.java8.zzzz.zero;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:24
 */
public interface IAnimal {

    default void breath(){
        System.out.println("breath!");
    }

    static void run(){
        System.out.println("-------static run ------");
    }

}
