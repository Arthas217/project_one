package org.com.zlk.genericstype.common;

public class GenericsMethodClass<T> {

    public void method1(T t) {
        System.out.println("入参T类型：" + t.getClass().getName());
    }

    /**
     * 泛型类中的类型参数T与泛型方法中的类型参数T是没有相应的联系的
     * 泛型方法始终以自己定义的类型参数为准。
     */
    public <T> T method2(T t) {
        System.out.println("入参T类型：" + t.getClass().getName());
        return t;
    }
}
