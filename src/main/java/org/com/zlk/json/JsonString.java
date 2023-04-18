package org.com.zlk.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.com.zlk.basic.dynamicproxy.cglib.demo2.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description: list集合对象转json字符串的使用
 * @Date 2022/8/19 16:09
 */
public class JsonString {

    public static void main(String[] args) {
        //1、如果需要将 List 中的元素作为 JSONObject 的属性值进行序列化，可以先将 List 转换为 Map，然后将 Map 序列化为 JSON 字符串
        listToMap4JSONObject();
        listToMap4JSONObject2();

        //2、不需要将 List 中的元素作为 JSONObject 的属性值进行序列化,直接使用 JSONArray.toJSONString 方法
        listToJSONString();
        listToJSONString2();


        test1();
        test2();
        test3();
    }



    private static void test3() {
        Map<String,Object> map2 = new HashMap<>();
        map2.put("colname","party_id");
        map2.put("value","2");
        List<Map<String,Object>> list1 = new ArrayList<>();
        list1.add(map2);
        System.out.println(JSONObject.toJSONString(list1));

        Map<String,String> map3 = new HashMap<>();
        map3.put("value","3");
        map2.put("new",map3);
        System.out.println(JSONObject.toJSONString(map2));
    }

    private static void test2() {
        JSONObject j1 = new JSONObject();
        j1.put("3","3");
        JSONObject j2 = new JSONObject();
        j2.put("4","4");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(j1);
        jsonArray.add(j2);
        System.out.println(jsonArray.toJSONString(jsonArray));
    }

    private static void test1() {
        Map<String,String> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        map.put("1","2");
        map1.put("2","2");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        System.out.println(JSONObject.toJSONString(list));
    }

    private static void listToJSONString2() {
        List<User> message1 = new ArrayList<>();
        User u1 = new User();
        u1.setAge(1);
        u1.setId("1");
        u1.setName("1");
        User u2 = new User();
        u2.setAge(2);
        u2.setId("2");
        u2.setName("2");
        message1.add(u1);
        message1.add(u2);
        String jsonString1 = JSONArray.toJSONString(message1);
        System.out.println(jsonString1);
    }

    private static void listToMap4JSONObject2() {
        List<User> message1 = new ArrayList<>();
        User u1 = new User();
        u1.setAge(1);
        u1.setId("1");
        u1.setName("1");
        User u2 = new User();
        u2.setAge(2);
        u2.setId("2");
        u2.setName("2");
        message1.add(u1);
        message1.add(u2);

        Map<String, User> map = message1.stream().collect(Collectors.toMap(k -> k.getName(), v -> v, (v1, v2) -> v2));
        String s = JSONObject.toJSONString(map);
        System.out.println(s);
    }

    private static void listToJSONString() {
        List<String> message1 = new ArrayList<>();
        message1.add("hello");
        message1.add("world");

        // 序列化 List 为 JSON 字符串
        String jsonString1 = JSONArray.toJSONString(message1);
        //["hello","world"]
        System.out.println(jsonString1);
    }

    private static void listToMap4JSONObject() {
        List<String> message = new ArrayList<>();
        message.add("hello");
        message.add("world");
        // 将 List 转换为 Map
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < message.size(); i++) {
            map.put(i + "", message.get(i));
        }
        // 序列化 Map 为 JSON 字符串
        String jsonString = JSONObject.toJSONString(map);
        // {"0":"hello","1":"world"}
        System.out.println(jsonString);
    }
}
