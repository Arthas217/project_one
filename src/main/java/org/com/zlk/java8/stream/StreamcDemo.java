package org.com.zlk.java8.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author 会游泳的蚂蚁
 * @Description: https://mp.weixin.qq.com/s/OIoW4nhCF9Yoyb4wdjG7NA
 * @Date 2022/4/12 15:30
 */
public class StreamcDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamcDemo.class);

    public static List<Employee> personList;

    public static void main(String[] args) {
//        basicMethod();
        basicData();
        // 遍历、匹配
//        foeachFind();
        // 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
//        filterCollect();
        // 聚合统计
//        maxMinCount();
        // 映射 map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。flatMap：接收一个函数作为参数，将流中的每个元素值都换成另一个流(多个元素)，然后把所有流连接成一个流。
        mapFlatMap();
    }

    private static void mapFlatMap() {
        //英文字符串数组的元素全部改为大写。
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strings = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        LOGGER.info("strArr value is  {}", strings);
        //整数数组每个元素+3
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        LOGGER.info("list  value is {}", intListNew);
        //将员工的薪资全部增加1000
        List<Integer> person = personList.stream().map(s -> s.getSalary() + 1000).collect(Collectors.toList());
        LOGGER.info("map Employee salary is {}  改变了原来员工集合的方式", person);
        List<Employee> collect = personList.stream().map(employee -> {
            employee.setSalary(employee.getSalary() + 1000);
            return employee;
        }).collect(Collectors.toList());
        LOGGER.info("map Employee salary is {}  改变了原来员工集合的方式", collect.get(0).getSalary());
        List<Employee> personListNew = personList.stream().map(p1 -> {
            Employee personNew = new Employee(p1.getName(), 0, 0, null, null);
            personNew.setSalary(p1.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        LOGGER.info("map Employee salary is {}, 不改变原来员工集合的方式", collect.get(0).getSalary());

        //将两个字符数组合并成一个新的字符数组
    }

    private static void maxMinCount() {
        //获取String集合中最长的元素
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> maxLen = list.stream().max(Comparator.comparing(String::length));
        LOGGER.info("max len string is {} ,len is {}", maxLen.get(), maxLen.get().length());

        //获取Integer集合中的最大值
        List<Integer> list1 = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max = list1.stream().max(Integer::compare);
        LOGGER.info("integer list max value {}", max.get());
        // 自定义排序
        Optional<Integer> max1 = list1.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        LOGGER.info("integer list max1 value {}", max1.get());

        // 获取员工工资最高的人(工资这里定义类型为int
        Optional<Employee> max2 = personList.stream().max(Comparator.comparingInt(Employee::getSalary));
        LOGGER.info("max2 salary value {}", max2.get().getSalary());

        // 计算Integer集合中大于6的元素的个数
        List<Integer> list2 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list2.stream().filter(v -> v > 6).count();
        LOGGER.info("value above six count {}", count);

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
        personList.add(new Employee("Tom", 8900, 23, "male", "New York"));
        personList.add(new Employee("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Employee("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Employee("Anni", 8200, 24, "female", "New York"));
        personList.add(new Employee("Owen", 9500, 25, "male", "New York"));
        personList.add(new Employee("Alisa", 7900, 26, "female", "New York"));
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
        int[] array = {1, 3, 5, 6, 8};
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
