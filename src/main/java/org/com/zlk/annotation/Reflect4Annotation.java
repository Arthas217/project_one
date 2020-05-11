package org.com.zlk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Reflect4Annotation {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
       Class student = Class.forName("org.com.zlk.annotation.Student");
        Annotation[] annotations = student.getAnnotations();

        // 反射针对都是类
        for (Annotation annotation: annotations) {
            System.out.println(annotation);
        }

        TableZlk tableZlk= (TableZlk)student.getAnnotation(TableZlk.class);
        System.out.println(tableZlk.tableName());

        // 获取student属性name  FieldZlk中是 @Target(ElementType.FIELD)
        Field f = student.getDeclaredField("name");
        // 获取自定义注解FieldZlk
        FieldZlk fieldZlk= f.getAnnotation(FieldZlk.class);
        System.out.println(fieldZlk.columnName());
        System.out.println(fieldZlk.type());
        System.out.println(fieldZlk.length());
    }
}
