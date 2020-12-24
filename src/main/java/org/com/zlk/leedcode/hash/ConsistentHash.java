package org.com.zlk.leedcode.hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法实现
 *
 * @Author zc217
 * @Date 2020/12/22
 */
public class ConsistentHash<T> {

    private final MessageDigest messageDigest;
    private final int numberOfReplicas;
    private final SortedMap<BigInteger, T> circle = new TreeMap<>();

    public ConsistentHash(int numberOfReplicas, Collection<T> nodes) {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            this.numberOfReplicas = numberOfReplicas;
            for (T node : nodes) {
                add(node);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            BigInteger hash = new BigInteger(messageDigest.digest((node.toString() + i).getBytes()));
            circle.put(hash, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            BigInteger hash = new BigInteger(messageDigest.digest((node.toString() + i).getBytes()));
            circle.remove(hash);
        }
    }

    public T get(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        BigInteger hash = new BigInteger(messageDigest.digest(key.getBytes()));
        if (!circle.containsKey(hash)) {
            SortedMap<BigInteger, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
