package org.com.zlk.company;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程高并发处理一个keyword的计数器
 *
 * @Author zc217
 * @Date 2020/7/2
 */
public class WordCount {

    private static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap();

    public static void incrWordCount(String key) {
        if (map.get(key) == null) {
            map.put(key, 1l);
        } else {
            map.put(key, map.get(key) + 1l);
        }
    }

    public static long getWordCount(String key) {
        return map.get(key);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100000; i++) {
            int temp = i;
            new Thread(() -> {
                incrWordCount(String.valueOf(new Random().nextInt(10) + 1));
//                System.out.println("第" + temp + "个线程" + Thread.currentThread());
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {// 主线程和GC线程
            // 线程礼让，让其他线程执行
            Thread.yield();
        }
        // 输出keyword和频次
        Set<Map.Entry<String, Long>> entries = map.entrySet();
        for (Map.Entry<String, Long> entry : entries) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        System.out.println("------------------");

        SortMap(map);

        System.out.println("-------------------");
        System.out.println(getWordCount("10"));

    }

    private static void SortMap(ConcurrentHashMap<String, Long> map) {
        ArrayList<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (int) (o2.getValue() - o1.getValue());
            }
        });

        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }
}
