package org.com.zlk.chxg;

import com.alibaba.fastjson.JSONObject;
import org.com.zlk.annotation.Student;
import org.com.zlk.basic.dynamicproxy.cglib.demo2.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/18 09:52
 */
public class Dict {

    public static void main(String[] args) {
        System.out.println(convertToQYNumber(1));

        // 创建一个包含Student对象的List集合
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, 1, "张三"));
        students.add(new Student(2, 2, "李四"));
        students.add(new Student(3, 3, "王五"));
        students.add(new Student(4, 3, "赵六"));

        // 将List<Student>转换为Map<Integer, String>，如果key冲突，保留旧值
        Map<Integer, String> studentMap1 = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getName, (oldValue, newValue) -> oldValue));
        System.out.println(studentMap1);

        List<Student> collect = students.stream().collect(Collectors.toList());
        collect.forEach(
                student -> {
                    System.out.println(student.getId());
                    System.out.println(student.getAge());
                    System.out.println(student.getName());
                }
        );

    }


    /**
     * 1->QY000001
     * @param number
     * @return
     */
    public static String convertToQYNumber(int number) {
        String prefix = "QY";
        String convertedNumber = String.format("%06d", number);
        return prefix + convertedNumber;
    }


}
