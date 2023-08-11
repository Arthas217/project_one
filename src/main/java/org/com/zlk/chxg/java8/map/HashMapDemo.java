package org.com.zlk.chxg.java8.map;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2023/5/30 19:26
 */
public class HashMapDemo {

    public static void resizeTest(){
        List<String> list = new ArrayList<>();
        list.add("jlkk");
        list.add("lopi");
        list.add("jmdw");
        list.add("e4we");
        list.add("io98");
        list.add("nmhg");
        list.add("vfg6");
        list.add("gfrt");
        list.add("alpo");
        list.add("vfbh");
        list.add("bnhj");
        list.add("zuio");
        list.add("iu8e");
        list.add("yhjk");
        list.add("plop");
        list.add("dd0p");
        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);//扰动函数， hash为key的哈希值
            System.out.println("字符串：" + key + " \tIdx(16)：" + ((32 - 1) & hash) + " \tBit值：" + Integer.toBinaryString(hash) + " - " + Integer.toBinaryString(hash & 32));
            System.out.println("字符串：" + key + " \tIdx(32)：" + ((64 - 1) & hash) + " Bit值：" + Integer.toBinaryString(hash) + " - " + Integer.toBinaryString(hash & 32));
        }
    }

    public static void main(String[] args) {
        //扩容元素时索引计算的规律（hash&oldCap不为1时 ，索引位置为=当前索引值 + oldCap  hash&oldCap为0，索引位置不变）
        resizeTest();
    }
}
