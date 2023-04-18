package org.com.zlk.chxg.java8.stream.collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.com.zlk.zhouyang.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description: List转Map
 * https://blog.csdn.net/u010046908/article/details/100580093
 * @Date 2022/8/18 10:40
 */
public class Stream4CollectorsToMap {

    public static void main(String[] args) {
        //涉及到stream流方式
        listToMap();
        ListToOtherMap();
    }

    private static void ListToOtherMap() {
        //1、List 转换 ConcurrentMap
        List<User> userList = getUserList();
        Map<Integer, User> listToConcurrentMap = listToConcurrentMap(userList);
        System.out.println(listToConcurrentMap instanceof ConcurrentHashMap);
        System.out.println(JSONObject.toJSONString(listToConcurrentMap));

        //2、List 转换 SortedMap
        List<User> userList2 = getUserList();
        TreeMap<Integer, User> listToSortedMap = listToSortedMap(userList2);
        System.out.println(listToSortedMap instanceof TreeMap);
        System.out.println(JSONObject.toJSONString(listToSortedMap));
    }



    private static TreeMap<Integer,User> listToSortedMap(List<User> userList) {
        return userList.stream().sorted(Comparator.comparing(User::getAge)).collect(
                Collectors.toMap(User::getAge, Function.identity(), (o1, o2) -> o1, TreeMap::new));
    }

    public static Map<Integer, User> listToConcurrentMap(List<User> userList) {
        return userList.stream().collect(Collectors.toMap(User::getAge, Function.identity(),
                (o1, o2) -> o1, ConcurrentHashMap::new));
    }


    private static void listToMap() {
        List<User> list = getUserList();
        //默认情况下，toMap()方法将返回一个HashMap。 第三个参数代表的含义是，key冲突的处理规则
        Map<String, Integer> result1 = list.stream().collect(Collectors.toMap(User::getName, User::getAge, (s, a) -> Integer.valueOf(s + a)));
        Map<String, Integer> result2 = list.stream().collect(Collectors.toMap(User::getName, User::getAge, (s, a) -> s));
        //直接输出map结构是k=v，k1=v1.....
        System.out.println("转为map  key冲突时相加" + result1);
        System.out.println("转为map  key冲突时保留旧值" + result2);
        //转化为json字符串返回{"1":44,"2":22}
        System.out.println(JSONArray.toJSON(result1));
        System.out.println(JSONObject.toJSON(result1));
    }

    private static List<User> getUserList() {
        User user1 = new User("1", 11);
        User user2 = new User("2", 22);
        User user3 = new User("1", 33);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
