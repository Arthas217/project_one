package org.com.zlk.java8.stream.functioninterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 内置Function函数式接口--代表接受一个输入并产生结果的操作
 * 如果你需要定义一个 Lambda，将输入对象的信息映射到输出，可以使用Function接口
 * @Date 2021/12/28 09:32
 */
public class FunctionTest {

    public static void main(String[] args) {
        //function主要方法compose和andThen
        basicTest();
        // function和toIntFunction区别在于它们的输入和输出类型不同。
        functionAndtoIntFuntion();
    }

    private static void functionAndtoIntFuntion() {
        Function<String, Integer> function_strlen = (String s) -> s.length();
        ToIntFunction<String> function_int = (String s) -> s.length();
        List<String> word = Arrays.asList("a", "abvc", "apple", "bike");
        List<Integer> len = map(word, function_strlen);
        List<Integer> len1 = map1(word, function_int);
        System.out.println(len);
        System.out.println(len1);
    }

    private static void basicTest() {
        //1、compose方法，接受一个输入并产生一个结果的操作。该接口中有一个compose()方法，用于将两个Function组合成一个新的Function。
        Function<Integer, Integer> f1 = x -> x + 1; // 加一
        Function<Integer, Integer> f2 = x -> x * 2; //乘以2

        //compose方式的c1等价于apply方式的c2
        Function<Integer, Integer> c1 = f2.compose(f1);
        Function<Integer, Integer> c2 = x -> f2.apply(f1.apply(x));
        System.out.println(c1.apply(1));
        System.out.println(c2.apply(1));


        // 2、andThen方法，接受一个作为参数的函数，并返回一个新的函数。这个新函数会先对当前函数进行计算，然后将计算结果作为参数传递给作为参数的函数再进行计算，最终返回一个最终的计算结果。
        Function<Integer, Double> f = x -> x * 0.5; // 输入 Integer，输出 Double
        Function<Double, String> g = y -> "Result: " + y; // 输入 Double，输出 String
        Function<Integer, String> h = f.andThen(g); // 先执行 f，再执行 g
        Function<Integer, String> j = x -> g.apply(f.apply(x)); // 等价于apply
        System.out.println(h.apply(10)); // 输出：Result: 5.0
        System.out.println(j.apply(10));


        Function<String, Integer> toInteger = Integer::valueOf;
        Function<Integer, String> backToString = String::valueOf;
        Function<String,String>  reverse = toInteger.andThen(backToString);
        System.out.println("reverse-----string to int and int to string "+reverse.apply("1"));

    }


    /**
     * list列表映射为每个T类型它的长度列表
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
