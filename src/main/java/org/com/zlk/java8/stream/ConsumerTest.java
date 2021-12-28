package org.com.zlk.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 09:19
 */
public class ConsumerTest {

    /**
     * Lambda是Consumer中accept方法的实现
     */
    private static Consumer<Integer> consumer = (Integer i) -> System.out.print(i + " ");

    public static <T> void forEachFuntion(List<T> list, Consumer<T> c) {
        for (T num : list) {
            c.accept(num);
        }
    }

    public static void main(String[] args) {
        forEachFuntion(Arrays.asList(1, 2, 3, 4, 5), consumer);
    }
}
