package org.com.zlk.basic;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description: null引用
 * https://blog.csdn.net/qq_25077777/article/details/80174763
 * @Date 2022/8/24 11:49
 */
public class NullDemo {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6(null);
        test7();
    }


    /**
     * null引用是null类型表达式唯一可能的值
     * null引用可以转换为任意引用类型。返回一个该类型对象的空引用(其实还是null)
     * null是java中的关键字,大小写敏感,null是任何引用类型的默认值
     * null既不是对象也不是一种类型，它仅是一种特殊的值，你可以将其赋予任何引用类型，你也可以将null转化成任何类型.但是不能将null赋值给基本类型变量
     */
    private static void test1() {
        String s1 = null;
        Integer integer = null;
        Double aDouble = null;
        String s2 = (String) null;
        Integer integer1 = (Integer) null;
        Double aDouble1 = (Double) null;
    }

    /**
     * 直接将null赋值给基本类型会出现编译错误。但将null赋值给包装类object，然后将object赋给各自的基本类型，编译不会报错，但运行会空指针，这是自动拆箱导致的。
     */
    private static void test2() {
        Integer i = null;
        int num = i;
    }

    /**
     * 和test2类似，num.get(n)返回的是Object类型
     */
    private static void test3() {
        Map map = new HashMap<>();
        int[] numbers = {1, 2, 3};
        for (int n : numbers) {
            int count = (int) map.get(n);//需要对null做判断
            map.put(n, count++);
        }
        System.out.println(JSONObject.toJSONString(map));
    }

    /**
     * 使用了带有null值的引用类型的变量，instanceof操作会返回false
     * 一个很重要的特性，使得对强制类型转换检查很有用
     */
    private static void test4() {
        Integer i = null;
        System.out.println(i instanceof Integer);
    }

    /**
     * 使用静态方法来使用一个值为null的引用类型变量。因为静态方法使用静态类型绑定，不会抛空指针异常
     */
    private static void test5() {
        NullDemo nullDemo = null;
        nullDemo.test55();
        nullDemo.test555();
    }

    private static void test55() {
        System.out.println("static method test55");
    }

    private void test555() {
        System.out.println("not static method test555");
    }

    /**
     * 可以将null传递给方法使用，这时方法可以接收任何引用类型，
     */
    private static void test6(Object obj) {
        System.out.println(obj);
    }

    /**
     * 可以使用== 或者 != 操作来比较null值，但是不能使用其他算法或者逻辑操作
     * null==null会返回true
     */
    private static void test7() {
        String a = null;
        String b = null;
        System.out.println(a == b);
        System.out.println(a != b);

    }
}
