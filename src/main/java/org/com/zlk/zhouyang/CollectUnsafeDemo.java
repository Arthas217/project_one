package org.com.zlk.zhouyang;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 */
public class CollectUnsafeDemo {

    public static void main(String[] args) {
        // ArrayList
        arrayListProblem();

        // LinkedList
        LinkedList();

        // set
        hashset();

        // TODO: 2020/6/15  
        // k,v对
        map();

    }

    private static void map() {
        // 无序
        HashMap map = new HashMap();
        map.put(1,1);
        map.put(1,2);

        // 并发安全
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        // 有序 默认为插入顺序
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        // 按照keys的自然排序排列
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
    }

    private static void hashset() {
        // 无序set capacity (16) and load factor (0.75)
        // map.put(e, PRESENT)只关心key
        HashSet<Integer> hashSet = new HashSet<>();
        Set<Integer> hashSet2 = Collections.synchronizedSet(new HashSet<>());
        Set<Integer> hashSet3 = new CopyOnWriteArraySet<>();
        // 有序set
        TreeSet<Integer> treeSet = new TreeSet<>();
    }

    private static void LinkedList() {
        // list中add方法 --> addLast  主要添加的节点在链表中的位置
        List<String> list = new LinkedList<>();
    }

    private static void arrayListProblem() {
        // 不安全、默认初始化大小10
        List<String> list1 = new ArrayList<>();
        //高并发时，会报出 java.util.ConcurrentModificationException
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                // ArrayList的add方法没有添加锁
                list1.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list1);
            },String.valueOf(i)).start();
        }

        // 并发安全解决方法
        // 1）Vector类--加锁synchronized
        // 2）synchronizedList
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        // 3）写时复制并发安全 看下add方法（ lock、getArray、Arrays.copyOf、setArray）
        List<String> list3 = new CopyOnWriteArrayList<>();
    }
}
