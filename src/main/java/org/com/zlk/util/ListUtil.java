package org.com.zlk.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2022/6/30 08:52
 */
public class ListUtil {


    /**
     * 合并多个集合list（集合不能为空）
     * @param lists
     * @return
     */
    public static List<String> ListIntegration(List<String>... lists) {
        return Arrays.stream(lists).flatMap(List::stream).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        l3.add("5");
        l3.add("6");
        List<String> fileIdList = ListIntegration(l1, l2, l3);
        System.out.println(fileIdList);

    }
}
