package org.com.zlk.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/8/19 16:09
 */
public class JsonString {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Map<String,String> map1 = new HashMap<>();
        map.put("1","2");
        map1.put("2","2");
        List<Map<String,String>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);
        System.out.println(JSONObject.toJSONString(list));

        JSONObject j1 = new JSONObject();
        j1.put("3","3");
        JSONObject j2 = new JSONObject();
        j2.put("4","4");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(j1);
        jsonArray.add(j2);
        System.out.println(jsonArray.toJSONString());

        Map<String,String> map2 = new HashMap<>();
        map2.put("colname","party_id");
        map2.put("value","2");
        List<Map<String,String>> list1 = new ArrayList<>();
        list1.add(map2);
        System.out.println(JSONObject.toJSONString(list1));
    }
}
