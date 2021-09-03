package org.com.zlk.java8.zzzz.two;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 12:09
 */
@FunctionalInterface
public interface IPersonFactory<P extends Person> {

    P create(String firstName, String lastName);
}
