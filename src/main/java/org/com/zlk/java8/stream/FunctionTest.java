package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/12/28 09:32
 */
public class FunctionTest {

    //如果你需要定义一个 Lambda，将输入对象的信息映 射到输出，就可以使用这个接口
    private static Function<String, Integer> function = (String s) -> s.length();

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

    public static void main(String[] args) {
        List<String> word = Arrays.asList("a", "abvc", "apple", "bike");
        List<Integer> len = map(word, function);
        System.out.println(len);
    }
}
