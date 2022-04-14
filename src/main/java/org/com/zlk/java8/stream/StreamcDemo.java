package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/OIoW4nhCF9Yoyb4wdjG7NA
 * @Date 2022/4/12 15:30
 */
public class StreamcDemo {

    public static List<Employee> personList;
    public static void main(String[] args) {
//        basicMethod();
        basicData();
        // 遍历、匹配
//        foeachFind();
        // 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
//        filterCollect();

    }

    private static void filterCollect() {
        // 筛选员工中工资高于8000的人，并形成新的集合
        List<String> name = personList.stream().filter(p -> p.getSalary() > 8000).map(pl -> pl.getName()).collect(Collectors.toList());
        System.out.println(name);
    }

    private static void foeachFind() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
    }

    private static void basicData() {
        personList = new ArrayList<>();
        personList.add(new Employee("Tom", 8900, 23,"male", "New York"));
        personList.add(new Employee("Jack", 7000, 25,"male", "Washington"));
        personList.add(new Employee("Lily", 7800, 21,"female", "Washington"));
        personList.add(new Employee("Anni", 8200, 24,"female", "New York"));
        personList.add(new Employee("Owen", 9500, 25,"male", "New York"));
        personList.add(new Employee("Alisa", 7900, 26,"female", "New York"));
    }

    private static void basicMethod() {
        // 用集合创建流
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        // 直接创建并行流，多个线程处理多个流（前提是各个流中的数据处理没有顺序要求）
        Stream<String> parallelStream = list.parallelStream();
        //通过parallel()把顺序流转换成并行流
        Stream<String> parallel = stream.parallel();

        // 用数组创建流
        int[] array={1,3,5,6,8};
        IntStream intStream = Arrays.stream(array);

        System.out.println(stream);
        System.out.println(parallelStream);
        System.out.println(intStream);

        // Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream1.forEach(System.out::print);
        System.out.println();
        stream2.forEach(System.out::print);
        System.out.println();
        stream3.forEach(System.out::print);
    }
}
