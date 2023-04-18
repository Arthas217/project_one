package org.com.zlk.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 内置Consumer函数式接口---代表接受单个输入参数并没有返回值的操作。
 * @Date 2021/12/28 09:19
 */
public class ConsumerTest {

    /**
     * 一个接受单个参数但没有返回值的Consumer
     * Lambda表达式是Consumer中accept方法的实现
     */
    private static Consumer<Integer> consumer = (value) -> System.out.println(value + "");

    public static <T> void forEachFuntion(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            // accept方法的作用是将该函数应用于给定的参数。
            consumer.accept(t);
        }
    }

    public static void main(String[] args) {
        //将每个元素作为参数传递给 consumer 进行处理
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        forEachFuntion(list, consumer);
    }
}
