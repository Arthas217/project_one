package org.com.zlk.java8.stream.functioninterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 内置Consumer函数式接口---代表接受单个输入参数并没有返回值的操作。
 * @Date 2021/12/28 09:19
 */
public class ConsumerTest {

    public static void main(String[] args) {
        consumer_accept(Arrays.asList(1, 2, 3, 4, 5), consumer);
        consumer_andThen();
    }

    private static void consumer_andThen() {
        // 一个用于打印大写字符串，另一个用于打印小写字符串
        Consumer<String> printUpperCase = str -> System.out.println(str.toUpperCase());
        Consumer<String> printLowerCase = str -> System.out.println(str.toLowerCase());
        // andThen方法，将两个 Consumer 组合成一个新的 Consumer，将第一个 Consumer 的输出作为第二个 Consumer 的输入。它会按照组合的顺序依次执行这两个 Consumer
        Consumer<String> reverse = printUpperCase.andThen(printLowerCase);
        reverse.accept("chxg");
    }


    /**
     * 一个接受单个参数但没有返回值的Consumer
     * Lambda表达式是Consumer中accept方法的实现
     */
    private static Consumer<Integer> consumer = (value) -> System.out.println(value + "");

    /**
     * 将每个元素作为参数传递给 consumer 进行处理
     *
     * @param list
     * @param consumer
     * @param <T>
     */
    public static <T> void consumer_accept(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            // accept方法的作用是将该函数应用于给定的参数。
            consumer.accept(t);
        }
    }


}
