package org.com.zlk.java8.book;

import com.google.common.base.Enums;

import java.util.stream.Stream;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 对于枚举类型来说，无法继承它，基本可以将它看作一个普通的类
 * @Date 2022/9/14 15:26
 */
public class EnumClassDemo {

    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
//        method4();
//        method5();
//        method6();
//        method7();
        method8();
    }

    private static void method8() {
        for (int i = 0; i < 20; i++) {
            System.out.print(EnumUtil.random(Activity.class) + " ");
        }
    }

    private static void method7() {
        //实现，而不是继承, 可以创建实现了一个或多个接口的枚举类型：
        //enum NotPossible extends Pet { ... // 无法执行,
        System.out.println(CartoonCharacter.BOB.get());
    }

    private static void method6() {
        //参考Reflection
        //Explore枚举被编译器限定为final类，所以你无法继承一个枚举类。此外还有一个static的初始化子句，你稍后会看到它可以被重定义。
        //values()方法是由编译器在枚举类的定义中插入的一个静态方法,values()方法是由编译器在枚举类的定义中插入的一个静态方法


        //如果向上转型枚举，便会丢失values()方法
        Explore[] vals = Explore.values();
        Enum e = Explore.HERE; // 向上转型,Enum中没有values()方法,但可以通过Class中有个getEnumConstants()方法获得实例
        for (Enum en : e.getClass().getEnumConstants()){
            System.out.println(en);
        }

        System.out.println(EnumClassDemo.class.getEnumConstants());
    }

    /**
     * 通常要使用枚举实例，就必须用枚举的类型名来限定它，但在switch case语句中你无须这么做
     */
    private static void method5() {
        //请看TrafficLight
    }



    /**
     * 重载枚举类型中的方法,和重载任何普通类的方法相同
     */
    private static void method4() {
        //重写toString方法，英文单词仅首字母为大写
        Stream.of(SpaceShipEnum.values()).forEach(System.out::println);
    }

    /**
     * 在枚举类型中增加自定义方法
     */
    private static void method3() {
        //默认的toString()方法只会返回枚举实例的名称
        System.out.println(SpicinessEnum.NOT.toString());
        //你可以实现一个构造方法，以获取额外的信息，然后再用额外的方法来提供扩展描述
        for (OzWitchEnum witch : OzWitchEnum.values())
            System.out.println("OzWitchEnum: " + witch + ", desc :" + witch.getDescription());
    }

    /**
     * 基本用法
     * ordinal()方法返回一个从0开始的int值，代表每个枚举实例的声明顺序。
     * 你可以放心地使用==来比较枚举实例（equals()和hashCode()方法会由编译器自动为你生成）。
     * Enum类实现了Comparable接口（因此可比较），所以自动包含了compareTo()方法，另外它还实现了Serializable接口（因此可序列化）。
     * 如果调用枚举实例的getDeclaringClass()方法，则会得到该枚举实例所属的外部包装类。
     * name()方法返回枚举实例被声明的名称，使用toString()同样也可以返回该名称。
     * valueOf()方法是Enum类中的静态方法，它根据传入的String，返回名称与该String匹配的枚举实例。如果匹配的实例不存在，则抛出异常。
     * <p>
     * 摘自：《On Java 中文版：进阶卷（试读本）》 — [美] 布鲁斯·埃克尔
     * 在豆瓣阅读书店查看：https://read.douban.com/ebook/346181575/
     */
    private static void method1() {
        for (ShrubberyEnum s : ShrubberyEnum.values()) {
            System.out.println(s + " ordinal: " + s.ordinal());//从0开始的int值，代表每个枚举实例的声明顺序
            System.out.print(s.compareTo(ShrubberyEnum.CRAWLING) + " ");
            System.out.print(s.equals(ShrubberyEnum.CRAWLING) + " ");
            System.out.println(s == ShrubberyEnum.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println(s.toString());
            System.out.println("********************");
        }

        // 根据字符串名生成一个枚举值：
        for (String s : "HANGING CRAWLING GROUND".split(" ")) {
            ShrubberyEnum shrub = Enum.valueOf(ShrubberyEnum.class, s);
            System.out.println(shrub);
        }
    }


    SpicinessEnum degree;

    public EnumClassDemo(SpicinessEnum degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "------EnumClassDemo{" +
                "degree=" + degree +
                '}';
    }

    /**
     * 构造方式
     * 注意，如果枚举定义在同一个文件中，或者定义在默认包中，则无法使用该方式
     */
    private static void method2() {
        System.out.println(new EnumClassDemo(SpicinessEnum.NOT));
        System.out.println(new EnumClassDemo(SpicinessEnum.MEDIUM));
        System.out.println(new EnumClassDemo(SpicinessEnum.HOT));
    }
}
