package org.com.zlk.datastructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    }
}
