package org.com.zlk.leedcode.company.toutiao;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author zc217
 * @Date 2020/8/17
 */
public class LRUCacheTest {

    @Test
    public void testLRUCache(){
        LRUCache2 cache = new LRUCache2(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(1,1);
//        cache.put(4,4);
//        cache.put(2,2);
        int value = cache.get();
        System.out.println(value);
    }

}