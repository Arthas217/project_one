package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 09:32
 */
public class FunctionTest {

    //如果你需要定义一个 Lambda，将输入对象的信息映x射到输出，就可以使用这个接口
    private static Function<String, Integer> function = (String s) -> s.length();

    private static ToIntFunction<String> function1 = (String s) -> s.length();

    /**
     * String 列表映射到包含 每个 String 长度的 Integer 列表
     */
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }


    /**
     * 只是表达ToIntFunction用法，结果又给包装起来了,没有什么意义
     */
    public static <T> List<Integer> map1(List<T> list, ToIntFunction<T> toIntFunction) {
        List<Integer> result = new ArrayList<>();
        for (T t : list) {
            int i = toIntFunction.applyAsInt(t);
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> word = Arrays.asList("a", "abvc", "apple", "bike");
        List<Integer> len = map(word, function);
        List<Integer> len1 = map1(word, function1);
        System.out.println(len);
        System.out.println(len1);
    }
}
