package org.com.zlk.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 通过反射获得Student类，进一步获取该类中自定义注解的值
 */
public class Reflect4Annotation {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {

        // 反射获取Class对象
        Class student = Class.forName("org.com.zlk.annotation.Student");

        // 获取该类注解
        Annotation[] annotations = student.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("获取student中注解： " + annotation);
        }

        // 获取student中注解StudentTableAnnotation的属性值
        StudentTableAnnotation studentAnnotation = (StudentTableAnnotation) student.getAnnotation(StudentTableAnnotation.class);
        System.out.println("student中 表名： " + studentAnnotation.tableName());

        // 获取student中属性
        Field field = student.getDeclaredField("name");
        // 获取student中属性的注解StudentFieldAnnotation
        StudentFieldAnnotation fieldAnnotation = field.getAnnotation(StudentFieldAnnotation.class);
        System.out.println("获取student属性name注解的值(列名称、类型、长度) ：" + fieldAnnotation.columnName() + " | " + fieldAnnotation.type() + " | " + fieldAnnotation.length());
    }
}
