package org.com.zlk.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 自定义字符串集合排序（ 以空格之前的字符串为比较对象）
 * @Date 2022/1/3 12:35
 */
public class ListStringSolution {

    public static void main(String[] args) {
        List<String> zones = new ArrayList<>();
        zones.add("10001 地方");
        zones.add("10004 水电费");
        zones.add("10005 打分");
        zones.add("10002 范德萨");
        zones.add("10003 发生了");
        zones.add("a 短的时间发");
        zones.add("b 时代峰峻了");

        Collections.sort(zones, (o1, o2) -> {
            String s1 = o1.substring(0, o1.indexOf(" "));
            String s2 = o2.substring(0, o2.indexOf(" "));
            return s1.compareTo(s2);
        });
        System.out.println(zones);
    }
}
