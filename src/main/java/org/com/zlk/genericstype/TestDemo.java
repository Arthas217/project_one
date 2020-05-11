package org.com.zlk.genericstype;

import org.com.zlk.model.Base;
import org.com.zlk.model.Sub;

import java.util.*;

/**
 * https://blog.csdn.net/briblue/article/details/76736356
 * 能广泛适用的类型(泛型）
 * 作用：参数化类型（ 类型当作参数传递给一个类或者是方法）
 * <p>
 * <p>
 * T 代表一般的任何类。
 * E 代表 Element 的意思，或者 Exception 异常的意思。
 * K 代表 Key 的意思。
 * V 代表 Value 的意思，通常与 K 一起配合使用。
 * S 代表 Subtype 的意思，文章后面部分会讲解示意。
 */
public class TestDemo {

    public static void main(String[] args) {
//        classtest();

//        mehodtest();

//        classAndMethod();

//        interfaceTest();

//        classtest1();

        List list1 = new ArrayList<Base>();
        List list2 = new ArrayList<Sub>();
        list1.add(new Base(1));
        list2.add(Sub.builder().value(2).num(4).build());
        list2.add(Sub.builder().value(22).num(44).build());

        mixTest(list1,list2);


    }

    private static <T> void mixTest(T t, Collection<? extends T> collection) {
        System.out.println(t);
        System.out.println("------------------------");
        Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    private static void interfaceTest() {
        GenericsInterface<Double> genericsInterface = t -> System.out.println(t);
        genericsInterface.method1(1d);
    }

    private static void classAndMethod() {
        GenericsMethodClass<String> genericsMethodClass = new GenericsMethodClass();
        genericsMethodClass.method1("zlk");
        Integer integer = genericsMethodClass.method2(1);
        System.out.println(integer);
    }

    private static void mehodtest() {
        GenericsMethod genericsMethod = new GenericsMethod();
        genericsMethod.method("sgc");
    }

    private static void classtest1() {
        GenericsClass1<Base, Sub> genericsClass1 = new GenericsClass1();
    }

    private static void classtest() {
        GenericsClass<String> genericsClass = new GenericsClass<>();
        genericsClass.setField("abc");
        System.out.println(genericsClass.getField());

        GenericsClass<Integer> genericsClass1 = new GenericsClass<>();
        genericsClass1.setField(1);
        System.out.println(genericsClass1.getField());
    }
}
