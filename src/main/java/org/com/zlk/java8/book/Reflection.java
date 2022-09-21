package org.com.zlk.java8.book;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 使用反射分析枚举类
 * @Date 2022/9/21 16:57
 */
public class Reflection {

    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("_____ Analyzing " + enumClass + " _____");
        System.out.println("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces())
            System.out.println(t);
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods())
            methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
        // 反编译enum：
//        OSExecute.command( "javap -cp build/classes/java/main Explore");
    }
    /* 输出：
    _____ Analyzing class Explore _____
    Interfaces:
    Base: class java.lang.Enum
    Methods:
    [compareTo, equals, getClass, getDeclaringClass,
    hashCode, name, notify, notifyAll, ordinal, toString,
    valueOf, values, wait]
    _____ Analyzing class java.lang.Enum _____
    Interfaces:
    java.lang.Comparable<E>
    interface java.io.Serializable
    Base: class java.lang.Object
    Methods:
    [compareTo, equals, getClass, getDeclaringClass,
    hashCode, name, notify, notifyAll, ordinal, toString,
    valueOf, wait]
    Explore.containsAll(Enum)? true
    Explore.removeAll(Enum): [values]

    Compiled from "Reflection.java"
    final class Explore extends java.lang.Enum<Explore> {
      public static final Explore HERE;
      public static final Explore THERE;
      public static Explore[] values();
      public static Explore valueOf(java.lang.String);
      static {};
    }
    */
}
