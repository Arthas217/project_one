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
 * https://blog.csdn.net/weixin_39973810/article/details/107978246
 * @Date 2022/4/12 15:30
 */
public class StreamcDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamcDemo.class);

    public static List<Employee> personList = new ArrayList<>();

    private static void basicData() {
        personList.add(new Employee("Tom", 8900, 23, "male", "New York"));
        personList.add(new Employee("Jack", 7800, 25, "male", "Washington"));
        personList.add(new Employee("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Employee("Anni", 8200, 24, "female", "New York"));
        personList.add(new Employee("Owen", 9500, 25, "male", "New York"));
        personList.add(new Employee("Alisa", 7900, 26, "female", "New York"));
    }

    public static void main(String[] args) {
        //获取部分属性
        getPartFiled();
        //=============================
//        basicMethod();
        basicData();
        // 遍历、匹配
//        foreachFind();
        // 筛选，是按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作。
//        filterCollect();
        // 聚合统计
//        maxMinCount();
        // 映射 map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。flatMap：接收一个函数作为参数，将流中的每个元素值都换成另一个流(多个元素)，然后把所有流连接成一个流。
//        mapFlatMap();
        // 归约，也称缩减,把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作
//        reduce();
        // 归约 reducing（Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持）
//        reducing();
        // 归集(toList/toSet/toMap/toCollection/toConcurrentMap)将流中的数据重新归集到新的集合里
//        collect();
        //统计(Collectors提供了一系列用于数据统计的静态方法eg,count,averagingInt、averagingLong、averagingDouble,maxBy、minBy,summingInt、summingLong、summingDouble,summarizingInt、summarizingLong、summarizingDouble)
//        statistic();
        // 分组（partitioningBy---分区：将stream按条件分为两个Map、groupingBy---将集合分为多个Map，有单级分组和多级分组）
//        xxBy();
        // 接合joining将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
//        xxJoining();
        //排序sorted，中间操作
//        xxSort();
        //提取/组合  流也可以进行合并、去重、限制、跳过等操作。
        extractCombination();

    }

    private static void extractCombination() {
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        LOGGER.info("流合并去重：" + newList);
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        LOGGER.info("限制：" + collect);
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(1).limit(5).collect(Collectors.toList());
        LOGGER.info("跳过限制：" + collect2);
    }

    private static void xxSort() {
        //sorted()：自然排序，流中元素需实现Comparable接口
        //sorted(Comparator com)：Comparator排序器自定义排序

        // 按工资升序排序（自然排序）
        List<Integer> sorted = personList.stream().sorted(Comparator.comparing(Employee::getSalary)).map(Employee::getSalary).collect(Collectors.toList());
        LOGGER.info("按工资升序排序（自然排序）{}", sorted);
        List<Integer> sorted2 = personList.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).map(Employee::getSalary).collect(Collectors.toList());
        LOGGER.info("按工资降序排序: {}", sorted2);

        //先按工资再按年龄自定义排序（降序）
        List<String> sorted3 = personList.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getAge)).map(Employee::getName).collect(Collectors.toList());
        LOGGER.info("先按工资再按年龄升序排序： {}", sorted3);

        // 先按工资再按年龄自定义排序（）
        List<String> sorted4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Employee::getName).collect(Collectors.toList());
        LOGGER.info("先按工资再按年龄(降序)： {}", sorted4);


    }

    private static void reducing() {
        //每个员工减去起征点后的薪资之和
        Integer sum = personList.stream().collect(Collectors.reducing(0, Employee::getSalary, (i, j) -> (i + j - 5000)));
        LOGGER.info("reducing 员工扣税薪资总和:{}", sum);
        Integer reduce = personList.stream().map(Employee::getSalary).reduce(0, (i, j) -> i + j - 5000);
        LOGGER.info("reduce 员工扣税薪资总和:{}", reduce);
    }

    private static void xxJoining() {
        String name = personList.stream().map(Employee::getName).collect(Collectors.joining(","));
        LOGGER.info("所有员工的姓名：{}", name);
    }

    private static void xxBy() {
        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Employee>> partitioningBySalary = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        LOGGER.info("员工按薪资是否大于8000分组情况：{}", partitioningBySalary);
        Map<String, List<Employee>> groupingBySex = personList.stream().collect(Collectors.groupingBy(x -> x.getSex()));
        LOGGER.info("将员工按性别分组：{}", groupingBySex);
        Map<String, Map<String, List<Employee>>> mix = personList.stream().collect(Collectors.groupingBy(Employee::getSex, Collectors.groupingBy(Employee::getArea)));
        LOGGER.info("员工按性别、地区 : {}", mix);
    }

    private static void statistic() {
        // 统计员工人数
        Long p_num = personList.stream().collect(Collectors.counting());
        LOGGER.info("统计员工人数 is {}", p_num);
        // 求平均工资
        Double averSalary = personList.stream().collect(Collectors.averagingInt(Employee::getSalary));
        LOGGER.info("平均工资 is {}", averSalary);
        Optional<Integer> highSalary = personList.stream().map(Employee::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        LOGGER.info("最高工资 is {}", highSalary.get());
        Integer sum = personList.stream().collect(Collectors.summingInt(Employee::getSalary));
        LOGGER.info("求工资之和 is {}", sum);
        DoubleSummaryStatistics statistics = personList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        LOGGER.info("一次性统计所有信息 is {}", statistics);
    }

    private static void collect() {
        Map<?, Employee> map = personList.stream()
                .filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Employee::getName, p -> p));
        LOGGER.info("toMap is {}", map);
    }

    private static void reduce() {
        //求Integer集合的元素之和、乘积和最大值
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum1 = list.stream().reduce(Integer::sum);
        Integer sum2 = list.stream().reduce(0, Integer::sum);
        LOGGER.info("reduce sum  value is  {}", sum.get());
        LOGGER.info("reduce sum  value is  {}", sum1.get());
        LOGGER.info("reduce sum  value is  {}", sum2);
        Optional<Integer> mul = list.stream().reduce((x, y) -> x * y);
        LOGGER.info("reduce mul  value is  {}", mul.get());
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max2 = list.stream().reduce(1, Integer::max);
        LOGGER.info("reduce max  value is  {}", max.get());
        LOGGER.info("reduce max  value is  {}", max2);

        //求所有员工的工资之和和最高工资。
        Optional<Integer> sumSalary = personList.stream().map(Employee::getSalary).reduce(Integer::sum);
        LOGGER.info("reduce sumSalary value is  {}", sumSalary.get());
        Integer sumValue = personList.stream().reduce(0, (result, e) -> result += e.getSalary(), (s1, s2) -> s1 + s2);
        Integer sumValue2 = personList.stream().reduce(0, (result, e) -> result += e.getSalary(), Integer::sum);
        LOGGER.info("reduce sumValue value is  {}", sumValue);
        LOGGER.info("reduce sumValue value2 is  {}", sumValue2);


    }


    private static <T> void printCollect(List<T> list) {
        list.stream().forEach(System.out::print);
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
        printCollect(personList);
        System.out.println();
        List<Integer> person = personList.stream().map(s -> s.getSalary() + 1000).collect(Collectors.toList());
        LOGGER.info("不改变了原来员工集合的方式11111  map Employee salary is {}  ", person);
        printCollect(personList);
        System.out.println();

        List<Employee> collect = personList.stream().map(employee -> {
            employee.setSalary(employee.getSalary() + 1000);
            return employee;
        }).collect(Collectors.toList());
        LOGGER.info("改变了原来员工集合的方式222222  map Employee salary is {}  ", collect.get(0).getSalary());
        printCollect(personList);
        System.out.println();

        List<Employee> personListNew = personList.stream().map(p1 -> {
            Employee personNew = new Employee(p1.getName(), 0, 0, null, null);
            personNew.setSalary(p1.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        LOGGER.info("不改变原来员工集合的方式33333   map Employee salary is {} ", personListNew.get(0).getSalary());
        printCollect(personList);
        System.out.println();

        //将两个字符数组(字符串)合并成一个新的字符数组
        List<String> list = Arrays.asList("m-k-l-a", "1-3-5-7");
        LOGGER.info("flatmap before list is {}", list);
        List<String> list1 = list.stream().flatMap(str -> {
            //将每个list中的每个元素转换成一个流
            String[] split = str.split("-");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        }).collect(Collectors.toList());
        LOGGER.info("flatMap after list is {}", list1);

        // 从热门活动中筛选满足有效活动的集合
        List<String> activeList = Arrays.asList("活dong有效1", "活dong有效2");
        List<String> hotList = Arrays.asList("活dong有效1 url://http", "活dongg url://http2", "活dong2 url://http2");
        List<String> result = activeList.stream().
                flatMap(active -> hotList.stream().filter(h -> h.substring(0, h.indexOf(' ')).equals(active)
                        && h.substring(0, h.indexOf(' ')).length() == active.length()))
                .collect(Collectors.toList());
        LOGGER.info("hot and effective active is {}", result);

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

    private static void foreachFind() {
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


    private static void getPartFiled() {
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("ally_no", "1");
        map1.put("card_level", "1");
        map1.put("a", "1");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("ally_no", "2");
        map2.put("card_level", "2");
        map2.put("a", "2");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("ally_no", "3");
        map3.put("card_level", "");
        map3.put("a", "");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("ally_no", "");
        map4.put("card_level", "");
        map4.put("a", "");
        Map<String, Object> map5 = new HashMap<>();
        map5.put("ally_no", "");
        map5.put("card_level", "5");
        map5.put("a", "5");
        listMap.add(map1);
        listMap.add(map2);
        listMap.add(map3);
        listMap.add(map4);
        listMap.add(map5);
        List<Map<String, Object>> mapList = new ArrayList<>();
        listMap.forEach(lMap -> {
            Map<String, Object> result = new HashMap<>();
            result.put("ally_no", lMap.get("ally_no"));
            result.put("card_level", lMap.get("card_level"));
            mapList.add(result);
        });
        System.out.println(mapList);

        List<Map<String, Object>> partField = listMap.stream().map(part -> {
            Map<String, Object> result = new HashMap<>();
            result.put("ally_no", part.get("ally_no"));
            result.put("card_level", part.get("card_level"));
            return result;
        }).collect(Collectors.toList());
        System.out.println(partField);

    }


}
