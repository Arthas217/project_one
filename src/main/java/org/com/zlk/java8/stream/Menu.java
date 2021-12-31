package org.com.zlk.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Description: 菜单类
 * @Date 2021/12/27 10:43
 */
public class Menu {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon",false,450,Dish.Type.FISH));


    public static void main(String[] args) {
        System.out.println(menu.stream().map(Dish::getName).collect(toList()));
        System.out.println(menu.stream().map(Dish::getName).map(String::length).collect(toList()));

        // 目的返回一张列表，列出里面各不相同的字符
        List<String> words = Arrays.asList("Hello", "World");
        // 类型Stream<String[]>    fail
        List<String[]> collect = words.stream().map(word -> word.split("")).distinct().collect(toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        // Arrays.stream()的方法可以接受 一个数组并产生一个流
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        System.out.println(streamOfwords.collect(toList()));

        // 返回的类型List<Stream<String>>   fail
        List<Stream<String>> li = words.stream().map(word -> word.split(""))
                .map(Arrays::stream).distinct().collect(toList());

        // success  使用映射flatmap方式
        List<String> charList = words.stream()
                 //将每个单词转换为由其字母构成的数组
                .map(word -> word.split(""))
                 //将各个生成流扁平化为单个流
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(charList);


        // 练习
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<Stream<int[]>> collect1 = numbers1.stream().map(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        List<int[]> collect2 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        List<int[]> collect3 = numbers1.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(toList());
        for (int[] arr : collect2) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println("=====================");
        }
        for (int[] arr : collect3) {
            for (int a : arr) {
                System.out.print(a + " ");
            }
            System.out.println("+++++++++++++++++++++");
        }


        Optional<Dish> any = menu.stream().filter(dish -> dish.getName().equals("KFC")).findAny();
        Dish dish = any.orElse(new Dish("KFC",false,800, Dish.Type.MEAT));
        System.out.println(dish.getName());
        if (any.isPresent()) {
            System.out.println(any.get().getName());
        }


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        // 归约，有初始值
        Integer reduce = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(reduce);

        Optional<Integer> optional = numbers.stream().reduce((a, b) -> (a + b));
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max.get());

        List<String> hot = new ArrayList<>();
        hot.add("edf 大望路");
        hot.add("abc 望京");
        hot.add("wed 阜通");
        hot.add("hrg 太阳宫");
        hot.add("UID 王府井");
        hot.add("ddf 西单");
        hot.add("sd 上地");

        List<String> z = new ArrayList<>();
        z.add("edf 大望路");
        z.add("wed 阜通");
        z.add("hrg 太阳宫");

        List<String> active = new ArrayList<>();
        active.add("sd");//上地
        active.add("hrg");//太阳宫
        active.add("ddf");//西单
        active.add("edf");//大望路
        active.add("abc");//望京
        active.add("wed");//阜通

        List<String> result = active.stream().flatMap(a -> hot.stream().filter(h -> h.contains(a))).collect(toList());
        System.out.println(result);


        List<String> zresult = active.stream().flatMap(a -> z.stream().filter(h -> h.contains(a))).collect(toList());
        System.out.println(zresult);
    }
}
