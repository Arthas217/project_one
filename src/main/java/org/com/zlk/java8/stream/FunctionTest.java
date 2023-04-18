package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 内置Function函数式接口--代表接受一个输入并产生结果的操作
 * @Date 2021/12/28 09:32
 */
public class FunctionTest {

    public static void main(String[] args) {
        basicTest();

//        List<String> word = Arrays.asList("a", "abvc", "apple", "bike");
//        List<Integer> len = map(word, function);
//        List<Integer> len1 = map1(word, function1);
//        System.out.println(len);
//        System.out.println(len1);
    }

    private static void basicTest() {
        //compose方法，接受一个输入并产生一个结果的操作。该接口中有一个compose()方法，用于将两个Function组合成一个新的Function。
        Function<Integer, Integer> f1 = x -> x + 1; // 加一
        Function<Integer, Integer> f2 = x -> x * 2; //乘以2

        //compose方式的c1等价于apply方式的c2，其内部实现也是用的apply来处理的。
        Function<Integer, Integer> c1 = f2.compose(f1);
        Function<Integer, Integer> c2 = x -> f2.apply(f1.apply(x));
        System.out.println(c1.apply(1));
        System.out.println(c2.apply(1));








    }

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


}
