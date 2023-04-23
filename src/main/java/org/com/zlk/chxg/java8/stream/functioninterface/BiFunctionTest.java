package org.com.zlk.chxg.java8.stream.functioninterface;

import java.util.function.BiFunction;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 类似Function接口
 * @Date 2023/4/20 17:27
 */
public class BiFunctionTest {

    public static void main(String[] args) {
        // apply方法使用
        bifunction_apply();
        //andThen方法
        bifunction_andthen();
    }

    private static void bifunction_andthen() {
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
        // 要使用的函数接口，先计算两个参数的乘机，然后在对结果加1
        BiFunction<Integer, Integer, Integer> multiAdd = multiply.andThen(c->add.apply(c,1));
        System.out.println(multiAdd.apply(34,2));
    }

    private static void bifunction_apply() {
        // 对两个参数做加法运算
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
        System.out.println(add.apply(12, 13));
    }

}