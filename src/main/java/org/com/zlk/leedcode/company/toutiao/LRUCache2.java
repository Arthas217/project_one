package org.com.zlk.leedcode.company.toutiao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 哈希表+链表
 */
public class LRUCache2 {

    private int cap;
    private Map<Integer, Integer> map;
    private LinkedList<Integer> list;

    public LRUCache2(int cap) {
        this.cap = cap;
        map = new HashMap<>();
        list = new LinkedList<>();
    }


    public int get(int key) {
        if (map.containsKey(key)) {
            list.remove(key - 1);
            list.addLast(key);
            return map.get(key);
        }
        return -1;
    }

    public int get() {
        return map.get(list.getFirst());
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            list.remove(key - 1);
            list.addLast(key);
            map.put(key, value);
            return;
        }
        if (list.size() == cap) {
            map.remove(list.removeFirst());
            map.put(key, value);
            list.addLast(key);
        } else {
            map.put(key, value);
            list.addLast(key);
        }
    }

}
