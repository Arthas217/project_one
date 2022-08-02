package org.com.zlk.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: JSONArray遍历
 * @Date 2022/8/2 09:33
 */
public class JSONArrayDemo {

    //1、for循环
    public static void method1(JSONArray jsonArr) {
        System.out.println("-----1-----");
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObject = jsonArr.getJSONObject(i);
            String name = jsonObject.getString("name");
            Integer age = (Integer) jsonObject.get("age");
            System.out.println("name:" + name + ";age:" + age);
        }
    }
    //2、JSONArray遍历stream流
    public static void method2(JSONArray dists) {
        System.out.println("-----2---------");
        Set<String> list=new HashSet<>();
        dists.stream().filter(Objects::nonNull).forEach(dist->{
            String name = (String)((JSONObject) dist).get("name");
            list.add(name);
        });
    }


    public static void main(String[] args) {
        String str = "[{\"name\":\"7190214\"},{\"name\":\"7190208\"},{\"name\":\"6811009\"},{\"name\":\"7040177\"},{\"name\":\"7070152\"},{\"name\":\"7060188\"},{\"name\":\"7050156\"},{\"name\":\"7020169\"},{\"name\":\"7010202\"}]";
        JSONArray array = JSONArray.parseArray(str);
        System.out.println(array);
        method1(array);
        method2(array);
    }

}
