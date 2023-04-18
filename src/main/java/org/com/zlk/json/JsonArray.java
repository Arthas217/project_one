package org.com.zlk.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: JSONArray遍历 put，get顺序一致
 * @Date 2022/8/2 09:33
 */
public class JsonArray {

    public static void main(String[] args) {
        // str可以去JsonString.java执行的结果，例如
        String str = "[{\"age\":1,\"id\":\"1\",\"name\":\"1\"},{\"age\":2,\"id\":\"2\",\"name\":\"2\"}]";
        JSONArray array = JSONArray.parseArray(str);
        System.out.println(array);
        method1(array);
        method2(array);
    }

    //for循环
    public static void method1(JSONArray jsonArr) {
        System.out.println("-----for循环方法遍历JSONArray-----");
        Set<String> set = new HashSet<>();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String name = jsonObject.getString("name");
            set.add(name);
        }
        System.out.println(set);
    }

    //JSONArray遍历stream流
    public static void method2(JSONArray jsonArray) {
        System.out.println("-----stream方式遍历JSONArray---------");
        Set<String> set = new HashSet<>();
        jsonArray.stream().filter(Objects::nonNull).forEach(object -> {
            String name = (String) ((JSONObject) object).get("name");
            set.add(name);
        });
        System.out.println(set);
    }




}
