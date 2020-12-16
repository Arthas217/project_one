package org.com.zlk.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zc217
 * @Date 2020/12/16
 */
public class MapSolutionTest {

    @Test
    public void testRankMapByValue() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("D", 1);
        hashMap.put("C", 2);
        hashMap.put("A", 3);
        hashMap.put("B", 2);
        hashMap.put("F", 1);
        hashMap.put("E", 0);
        MapSolution.rankMapByValue(hashMap, Integer::compare);
        System.out.println();
    }
}