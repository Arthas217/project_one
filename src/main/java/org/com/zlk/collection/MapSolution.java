package org.com.zlk.collection;

import org.apache.commons.collections.MapUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 根据Key或Value对Map进行排序及其应用
 * https://blog.csdn.net/justloveyou_/article/details/71658696
 *
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


    /**
     * 获取卡最高等级
     *
     * @param map
     * @param valueComparator
     * @param <K>
     * @param <V>
     */
    public static <K, V> void highLevelMapByValue(Map<K, V> map, final Comparator<V> valueComparator) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> valueComparator.compare(o1.getValue(), o2.getValue()));
        for (Map.Entry<K, V> entry : list) {
            System.out.println("Key : " + entry.getKey() + " , Value : " + entry.getValue());
            System.out.println(CardHighLevelEnum.getCardLevelName((Integer) entry.getValue()));
            break;
        }
    }


    public static String getHighLevel(List<Map<String, Object>> list) {
        Map<String, Map<Integer, String>> highMap = new ConcurrentHashMap<>();
        Map<Integer, String> p1 = new ConcurrentHashMap<>();
        Map<Integer, String> p2 = new ConcurrentHashMap<>();
        Map<Integer, String> p3 = new ConcurrentHashMap<>();
        Map<Integer, String> p4 = new ConcurrentHashMap<>();
        Map<Integer, String> p5 = new ConcurrentHashMap<>();
        Map<Integer, String> p6 = new ConcurrentHashMap<>();
        Map<Integer, String> p7 = new ConcurrentHashMap<>();
        p1.put(1, "4");
        p2.put(2, "5");
        p3.put(3, "5");
        p4.put(4, "5");
        p5.put(5, "5");
        p6.put(6, "5");
        p7.put(7, "5");
        highMap.put("46001121", p1);
        highMap.put("5", p2);
        highMap.put("6", p3);
        highMap.put("46001120", p4);
        highMap.put("2", p5);
        highMap.put("0", p6);
        highMap.put("1", p7);
        TreeMap sortMap = new TreeMap();
        for (Map<String, Object> map : list) {
            String allyNo = (String) map.get("allyNo");
            String cardLevel = (String) map.get("cardLevel");
            Map<Integer, String> allyNoMap = highMap.get(allyNo);
            Map<Integer, String> cardLevelMap = highMap.get(cardLevel);
            if (MapUtils.isNotEmpty(allyNoMap)) {
                sortMap.putAll(allyNoMap);
            }
            if (MapUtils.isNotEmpty(cardLevelMap)) {
                sortMap.putAll(cardLevelMap);
            }
        }
        return sortMap.firstEntry() == null ? "" : (String) sortMap.firstEntry().getValue();
    }

}
