package org.com.zlk.genericstype;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *  类型擦除，是泛型能够与之前的 java 版本代码兼容共存的原因。
 *  但也因为类型擦除，它会抹掉很多继承相关的特性，这是它带来的局限性。
 */
public class TypeErasure<T extends String> {

    private T field;

    public TypeErasure(T data) {
        this.field = data;
    }



}
