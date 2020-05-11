package org.com.zlk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 通过反射获得Student类，进一步获取该类中自定义注解的值
 */
public class Reflect4Annotation {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class student = Class.forName("org.com.zlk.annotation.Student");
        Annotation[] annotations = student.getAnnotations();

        // 反射针对都是类
        for (Annotation annotation : annotations) {
            System.out.println("该类： " + annotation);
        }

        StudentTableAnnotation studentAnnotation = (StudentTableAnnotation) student.getAnnotation(StudentTableAnnotation.class);
        System.out.println("student中 表名： " + studentAnnotation.tableName());

        //  FieldZlk中是 @Target(ElementType.FIELD)
        Field field = student.getDeclaredField("name");
        // 获取自定义注解FieldZlk
        StudentFieldAnnotation fieldAnnotation = field.getAnnotation(StudentFieldAnnotation.class);
        System.out.println("获取student属性name 注解的值(列名称、类型、长度) ：" + fieldAnnotation.columnName() + fieldAnnotation.type() + fieldAnnotation.length());
    }
}
