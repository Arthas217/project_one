package org.com.zlk.java8.zzzz.two;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/8/4 11:38
 */
public class test_02 {

    public static String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }

    public static void main(String[] args) {
        // 引用静态方法
        IConverter<Integer, String> converter02 = String::valueOf;

        // 引用普通方法
        IConverter<String, String> converter03 = test_02::startsWith;

        // 引用构造函数
        IPersonFactory<Person> iPersonFactory = Person::new;
        iPersonFactory.create("z","c");


        // 访问局部变量,但是这个num是不可变值，这样改变值会报错；在lambda表达式内部修改也是不允许的；
        int num = 1;
        IConverter<Integer, String> stringConverter = from -> String.valueOf(from + num);
        String convert = stringConverter.convert(2);
        System.out.println(convert); // 3


        //  Lambda 表达式中对成员变量和静态变量拥有读写权限：
        //  带有默认实现的接口方法，是不能在 lambda 表达式中访问的，代码将无法被编译通过。

    }
}
