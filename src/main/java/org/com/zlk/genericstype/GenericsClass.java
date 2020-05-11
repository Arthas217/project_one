package org.com.zlk.genericstype;

import lombok.Data;

/**
 * 泛型类
 * 不至接受一个类型参数，它还可以这样接受多个类型参数。
 */
@Data
public class GenericsClass<T> {
    T field;
}
