package org.com.zlk.collection;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.com.zlk.collection.MapSolution.getHighLevel;

/**
 * @Author zc217
 * @Date 2020/12/16
 */
public class MapSolutionTest {

    @Test
    public void testRankMapByValue() {
//        Map<String, Integer> hashMap = new HashMap<>();
//        hashMap.put("D", 1);
//        hashMap.put("C", 2);
//        hashMap.put("A", 3);
//        hashMap.put("B", 2);
//        hashMap.put("F", 1);
//        hashMap.put("E", 0);
//        MapSolution.rankMapByValue(hashMap, Integer::compare);
//        System.out.println();


        List<Map<String, Object>> list = buildList();
        // 卡最高等级
        Map<String, Integer> highLevel = getHighLevelMap(list);
        MapSolution.highLevelMapByValue(highLevel, Integer::compare);
        System.out.println();
        // 卡最高等级
        System.out.println(getHighLevel(list));

    }

    private Map<String, Integer> getHighLevelMap(List<Map<String, Object>> list) {
        Map<String, Integer> highLevel = new HashMap<>();
        for (Map<String, Object> map : list) {
            String allyNo = (String) map.get("allyNo");
            String cardLevel = (String) map.get("cardLevel");
            if (StringUtils.isNotBlank(allyNo)) {
                handlerMap(allyNo, highLevel);
            }
            if (StringUtils.isNotBlank(cardLevel)) {
                handlerMap(cardLevel, highLevel);
            }
        }
        return highLevel;
    }

    private List<Map<String, Object>> buildList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("allyNo", "6");
        m1.put("cardLevel", "");
        Map<String, Object> m2 = new HashMap<>();
        m2.put("allyNo", "");
        m2.put("cardLevel", "5");
        Map<String, Object> m3 = new HashMap<>();
        m3.put("allyNo", "1");
        m3.put("cardLevel", "2");
        Map<String, Object> m4 = new HashMap<>();
        m4.put("allyNo", "46001120");
        m4.put("cardLevel", "6");
        Map<String, Object> m5 = new HashMap<>();
        m5.put("allyNo", "5");
        m5.put("cardLevel", "2");
        Map<String, Object> m6 = new HashMap<>();
        m6.put("allyNo", "5");
        m6.put("cardLevel", "2");
        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);
        list.add(m6);
        return list;
    }

    private void handlerMap(String key, Map<String, Integer> highLevel) {
        switch (key) {
            case "46001121":
                highLevel.put(key, 1);
                break;
            case "5":
                highLevel.put(key, 2);
                break;
            case "6":
                highLevel.put(key, 3);
                break;
            case "46001120":
                highLevel.put(key, 4);
                break;
            case "2":
                highLevel.put(key, 5);
                break;
            case "0":
                highLevel.put(key, 6);
                break;
            case "1":
                highLevel.put(key, 7);
                break;
            default:
                throw new IllegalArgumentException("Unknow key  " + key);
        }
    }


}