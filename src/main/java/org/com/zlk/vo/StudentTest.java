package org.com.zlk.vo;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/7/1 12:45
 */
public class StudentTest {

    public static Student chainWay() {
        Student student = new Student().setAge(20).setScore(20);
        return student;
    }

    public static Student builderWay() {
        Builder<Student> builder = Builder.of(Student::new);
        builder.with(Student::setAge, 10);
        builder.with(Student::setScore, 10);
        return builder.build();
    }

    public static void main(String[] args) {
        System.out.println(builderWay().getAge());
        System.out.println(chainWay().getAge());
    }
}
