package org.com.zlk.java8.zzzz.pfscc;

import java.util.Objects;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 12:27
 */
public class PredicateTest {

    public static void main(String[] args) {
        java.util.function.Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("a"));
        System.out.println(predicate.negate().test("b"));
        java.util.function.Predicate result = predicate.and((s -> s.length()<2));
        System.out.println(result.test("1"));

        java.util.function.Predicate predicate1 = Objects::nonNull;
        java.util.function.Predicate predicate2 = Objects::isNull;
        java.util.function.Predicate<String> isEmpty = String::isEmpty;
        java.util.function.Predicate<String> isNotEmpty = isEmpty.negate();
    }
}
