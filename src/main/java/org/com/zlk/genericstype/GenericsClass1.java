package org.com.zlk.genericstype;


/**
 * 类型参数适用于参数之间的类别依赖关系
 * @param <T>
 * @param <E>
 */
public class GenericsClass1<T,E extends T> {
    T value1;
    E value2;

    public E getValue2(){
        return value2;
    }

    public T getValue1(){
        return value1;
    }
}
