package org.com.zlk.datastructure.list;

import com.google.common.collect.ImmutableList;

import java.util.*;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/4/7 16:46
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> li = new ArrayList<>();
        List<String> ll = new ArrayList<>(2);
        ll.add("1");
        ll.add("2");
        ll.add("3");
        ll.add(2, "1");
        List<String> l = new ArrayList<String>(Arrays.asList("1"));
        ArrayList<Integer> lis = new ArrayList<>(Collections.nCopies(10, 0));
        ll.remove(1);


        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println("通过数组转换:" + (list1.toArray().getClass() == Object[].class));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));
        System.out.println("通过集合转换:" + (list2.toArray().getClass() == Object[].class));

        List<String> l1 = Arrays.asList("1");
//        ArrayList<String> l2 = l1;
//        l1.add("1");


        List<String> list = new ArrayList<String>(Collections.<String>nCopies(8, "0"));
        list.set("a".hashCode() & 8 - 1, "a");
        list.set("b".hashCode() & 8 - 1, "b");
        list.set("c".hashCode() & 8 - 1, "c");
        list.set("d".hashCode() & 8 - 1, "d");
        list.set("e".hashCode() & 8 - 1, "e");
        list.set("f".hashCode() & 8 - 1, "f");
        list.set("g".hashCode() & 8 - 1, "g");

        // add 2021-1-3 使用addAll和immutable
        List<String> str = new ArrayList<>();
        str.add("abc");
        str.add("bcd");
        str.add("cde");
        str.add("def");
        str.add("efg");
        str.add("fgh");


        // 不能修改因为是immutable不可变类  newStr.remove(s) 报错java.lang.UnsupportedOperationException
        List<String> newStr = ImmutableList.copyOf(str);
        System.out.println(newStr == str);

        List<String> otherStr = new ArrayList<>();
        System.out.println(otherStr == str);
        otherStr.addAll(str);
        // 不能修改集合 例如otherStr.remove(s)  报错java.util.ConcurrentModificationException
        Iterator<String> iterator = otherStr.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (value.contains("a")) {
                iterator.remove();
            } else {
                System.out.print(value + " ");
            }
        }
    }
}
