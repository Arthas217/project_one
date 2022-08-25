package org.com.zlk.genericstype.trap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:泛型注意陷阱 https://www.cnblogs.com/hongdada/p/10683795.html
 * @Date 2022/8/25 16:58
 */
public class GenericsTrapDemo {


    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * <? extends T> 与 <? super T>
     */
    private static void test3() {
        // <? extends T> a，a 这个变量可以接受 T 及其 T 子类的集合，上界为 T，并且从 a 取出来的类型都会被强制转换为 T

    }

    /**
     * List,List<Object>区别
     */
    private static void test1() {
        List<Integer> t1 = new ArrayList<>();
        List t2 = t1;
        System.out.println(t1 == t2);
        // 编译报错 List<Object> t3 = t1;
    }

    /**
     * List<?>注意点
     * List<?> 是一个泛型，在没有赋值之前，是可以接受任何集合的赋值的，但是请注意，赋值之后就不能往里面添加元素了
     * 所以 List<?> 一般用来作为参数来接受外部的集合，或者返回一个不知道具体元素的集合。
     */
    private static void test2() {
        List<Object> t1 = new ArrayList<>();
        t1.add("1");
        t1.add("2");
        List<?> t2 = t1;
        // 编译通过
        t2.remove(0);
        // 编译不通过  t2.add(new Object());
        System.out.println(t1);
        System.out.println(t2);
        t2.clear();
        System.out.println(t1);
        System.out.println(t2);
    }



}
