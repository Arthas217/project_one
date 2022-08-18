package org.com.zlk.java8.stream;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.com.zlk.zhouyang.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * https://blog.csdn.net/u010046908/article/details/100580093
 * @Date 2022/8/18 10:40
 */
public class CollectorsDemo {

    public static void main(String[] args) {
        testToMap();
    }

    private static void testToMap() {
        User user1 = new User("1",11);
        User user2 = new User("2",22);
        User user3 = new User("1",33);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Map<String,Integer> result1 = list.stream().collect(Collectors.toMap(User::getName,User::getAge,(s, a) -> Integer.valueOf(s + a)));
        Map<String,Integer> result2 = list.stream().collect(Collectors.toMap(User::getName,User::getAge,(s, a) -> s));
//        System.out.println(result1);
//        System.out.println(result2);
        System.out.println(JSONArray.toJSON(result1));
        System.out.println(JSONObject.toJSON(result1));
    }
}
