package org.com.zlk.collection;

import java.util.*;

/**
 * 根据Key或Value对Map进行排序及其应用
 * https://blog.csdn.net/justloveyou_/article/details/71658696
 * @Author zc217
 * @Date 2020/12/16
 */
public class MapSolution {

    public static <K, V> void rankMapByValue(Map<K, V> map, final Comparator<V> valueComparator) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> valueComparator.compare(o1.getValue(), o2.getValue()));
        for (Map.Entry<K, V> entry : list) {
            System.out.println("Key : " + entry.getKey() + " , Value : " + entry.getValue());
        }
    }

}
