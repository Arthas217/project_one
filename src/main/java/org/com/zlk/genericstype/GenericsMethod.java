package org.com.zlk.genericstype;


/**
 * 泛型方法
 * <T>是声明、<T>中T被称为类型参数
 * 方法中参数T被称为参数化类型
 * 返回值void
 */
public class GenericsMethod {
    public <T> void method(T t) {
        System.out.println(t);
    }
}
