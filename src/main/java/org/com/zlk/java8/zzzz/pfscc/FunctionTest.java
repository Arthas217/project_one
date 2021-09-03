package org.com.zlk.java8.zzzz.pfscc;

import java.util.function.Function;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 12:33
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("abc"));
    }
}
