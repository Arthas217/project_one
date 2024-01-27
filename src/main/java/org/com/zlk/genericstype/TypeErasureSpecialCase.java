package org.com.zlk.genericstype;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TypeErasureSpecialCase {

    public static void main(String[] args) {
        //类型擦除 类型对于jvm来说都是List
//        case1();
        //泛型类在类型擦除情况
        case2();
        //通过反射 绕过泛型编译器的检测
//        case3();
    }

    private static void case1() {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        System.out.println(l1.getClass() == l2.getClass());
    }

    private static void case2() {
        // 如果没有指定上限 如<T> 则会被转译成普通的Object类型
        TypeErasureString();
        // 如果指定了上限 如<T extends String> 则类型参数就被替换成类型上限
        TypeErasureUpString();
    }

    private static void TypeErasureString() {
        TypeErasure2<String> typeErasure = new TypeErasure2<>("hello");
        Class typeErasureClass = typeErasure.getClass();
        System.out.println("类型擦除使用的类:" + typeErasureClass.getName());
        Field[] fields = typeErasureClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("类型擦除TypeErasure2 属性Field名称为： " + f.getName() + " 属性type为: " + f.getType().getName());
        }
    }

    /**
     * 指定了上限 <T extends String> 则类型参数就被替换成类型上限
     */
    private static void TypeErasureUpString() {
        TypeErasure<? extends String> typeErasure = new TypeErasure<>("hello");
        Class typeErasureClass = typeErasure.getClass();
        System.out.println("类型擦除使用的类:" + typeErasureClass.getName());
        Field[] fields = typeErasureClass.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("类型擦除TypeErasure 属性Field名称为： " + f.getName() + " 属性type为: " + f.getType().getName());
        }
    }

    private static void case3() {
        List<Integer> list = new ArrayList<>();
        list.add(23);
        try {
            Method method = list.getClass().getDeclaredMethod("add", Object.class);
            method.invoke(list, "test");
            method.invoke(list, 42.9f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object obj : list) {
            System.out.println(obj);
        }
    }


}
