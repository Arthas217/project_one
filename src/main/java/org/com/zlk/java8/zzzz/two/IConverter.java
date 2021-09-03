package org.com.zlk.java8.zzzz.two;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:54
 */
@FunctionalInterface
public interface IConverter<F, T> {

    T convert(F from);

    default void run() {

    }

    boolean equals(Object obj);
}
