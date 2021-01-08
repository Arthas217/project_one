package org.com.zlk.reflect;

import java.lang.annotation.*;

/**
 * @Author zc217
 * @Date 2021/1/4
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Inherited
public @interface Permission {

    String value();
}
