package org.com.zlk.chxg.java8.stream.functioninterface;

import java.util.function.Supplier;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  内置Supplier函数式接口---代表提供一个结果的操作，没有输入参数。
 * 在实际应用中，Supplier通常被用作一种延迟加载的机制，即只有在需要时才会生成数据。
 * 它可以与Lambda表达式和方法引用等函数式编程特性一起使用。
 *
 * @Date 2023/4/20 17:17
 */
public class SupplierTest {

    public static void main(String[] args) {

        // get()方法是一个无参方法，它的作用是获取供应者提供的对象,可以将其视为一个生产者，它可以不断地生成数据。
        // 当我们需要使用一些数据时，我们可以通过调用get()方法从Supplier中获取这些数据。
        supplier_get();
    }

    private static void supplier_get() {
        Supplier<String> supplier = () -> "Hello, World!";
        String result = supplier.get();
        System.out.println(result);
    }
}
