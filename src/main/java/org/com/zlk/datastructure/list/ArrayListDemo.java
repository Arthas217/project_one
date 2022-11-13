package org.com.zlk.datastructure.list;

import com.google.common.collect.ImmutableList;

import java.util.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  数组注意点
 * @Date 2021/4/7 16:46
 */
public class ArrayListDemo {

    public static void main(String[] args) {
//        array_add_remove();
//        diff_arrayList();
//        collections_ncopy();
        addAll_immutable();
    }

    private static void addAll_immutable() {
        List<String> str = new ArrayList<>();
        str.add("abc");
        str.add("bcd");
        str.add("cde");
        str.add("def");
        str.add("efg");
        str.add("fgh");
        // 不能修改因为是immutable不可变类
        List<String> immutableList = ImmutableList.copyOf(str);
        System.out.println(immutableList == str);
//        traverse(immutableList);//报错java.lang.UnsupportedOperationException

        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(str);
        System.out.println(arrayList == str);
        traverse(arrayList);
    }

    /**
     * 遍历且操作包含a字符的remove
     * @param list
     */
    private static void traverse(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (value.contains("a")) {
                iterator.remove();
            } else {
                System.out.print(value + " ");
            }
        }
    }

    private static void collections_ncopy() {
        List<String> list = new ArrayList<>(Collections.<String>nCopies(8, "0"));
        System.out.println(list);
        list.set("a".hashCode() & 8 - 1, "a");
        list.set("b".hashCode() & 8 - 1, "b");
        list.set("c".hashCode() & 8 - 1, "c");
        list.set("d".hashCode() & 8 - 1, "d");
        list.set("e".hashCode() & 8 - 1, "e");
        list.set("f".hashCode() & 8 - 1, "f");
        list.set("g".hashCode() & 8 - 1, "g");
        list.set("h".hashCode() & 8 - 1, "h");
        System.out.println(list);
    }

    private static void diff_arrayList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println(list1.getClass());// Arrays$ArrayList
        System.out.println("通过数组转换:" + (list1.toArray().getClass() == Integer[].class));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println("通过集合转换:" + (list2.toArray().getClass() == Object[].class));

        List<String> l1 = Arrays.asList("1");// Arrays的内部类
        l1.add("1");// 报错UnsupportedOperationException
//        ArrayList<String> l2 = l1;  //l2 属于java.util下的ArrayList类
        System.out.println(l1);
    }

    private static void array_add_remove() {
        List<String> l1 = new ArrayList<>(2);
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l1.add(2, "1");
        // 自动扩容
        System.out.println(l1);
        l1.remove(1);
        System.out.println(l1);

        List<String> l2 = new ArrayList<>(Arrays.asList("1"));
        l2.add("2");
        System.out.println(l2);
        l2.remove(0);
        System.out.println(l2);
        ArrayList<Integer> l3 = new ArrayList<>(Collections.nCopies(10, 0));
        l3.add(1);
        System.out.println(l3);
        l3.remove(l3.size() - 1);
        System.out.println(l3);
    }
}
