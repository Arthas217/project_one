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
public class JSONArrayDemo {

    //1、for循环
    public static void method1(JSONArray jsonArr) {
        System.out.println("-----1-----");
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String name = jsonObject.getString("name");
            System.out.println("name:" + name);
        }
    }

    //2、JSONArray遍历stream流
    public static void method2(JSONArray dists) {
        System.out.println("-----2---------");
        Set<String> set = new HashSet<>();
        dists.stream().filter(Objects::nonNull).forEach(dist -> {
            String name = (String) ((JSONObject) dist).get("name");
            set.add(name);
        });
        System.out.println(set);
    }


    public static void main(String[] args) {
        String str = "[{\"name\":\"a\"},{\"name\":\"b\"},{\"name\":\"c\"},{\"name\":\"d\"},{\"name\":\"e\"},{\"name\":\"f\"},{\"name\":\"g\"},{\"name\":\"h\"},{\"name\":\"i\"}]";
        JSONArray array = JSONArray.parseArray(str);
        System.out.println(array);
        method1(array);
        method2(array);
    }

}
