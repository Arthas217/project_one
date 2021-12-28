package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/27 22:18
 */
public class PredicateTest {

    private static Predicate<String> p = (String str) -> str.length() > 2;

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> word = Arrays.asList("ab", "abc", "bcd");
        List<String> filter = filter(word, p);
        System.out.println(filter);
    }

}
