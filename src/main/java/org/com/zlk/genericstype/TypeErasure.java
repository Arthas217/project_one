package org.com.zlk.genericstype;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *  泛型类 被类型擦除的时候
 *  类型参数部分 T
 *  如果没有指定上限<T> 则会被转译成普通的 Object 类型
 *  如果指定了上限 <T extends String> 则类型参数就被替换成类型上限
 *
 *  类型擦除，是泛型能够与之前的 java 版本代码兼容共存的原因。但也因为类型擦除，它会抹掉很多继承相关的特性，这是它带来的局限性。
 */
public class TypeErasure<T extends String> {

    T value;

    public TypeErasure(T object) {
        this.value = object;
    }

    public static void main(String[] args) {
        // 类型擦除
        TypErasureDemo();
        // 指定了上限 <T extends String> 则类型参数就被替换成类型上限
        TypeErasureString();
    }

    private static void TypeErasureString() {
        TypeErasure<String> erasure = new TypeErasure<>("hello");
        Class tpClass = erasure.getClass();
        System.out.println("erasure class is:" + tpClass.getName());
        Field[] fs = tpClass.getDeclaredFields();
        for (Field f : fs) {
            System.out.println("Field: " + f.getName() + " type: " + f.getType().getName());
        }
    }

    private static void TypErasureDemo() {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        System.out.println(l1.getClass() == l2.getClass());
    }
}
