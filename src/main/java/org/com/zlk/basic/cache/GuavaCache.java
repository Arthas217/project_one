package org.com.zlk.basic.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.com.zlk.company.toutiao.LRU;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GuavaCache {

    private static Map<String,Integer> service = new HashMap<>();


    static {
        // Guava Cache的使用
        LoadingCache<String, Integer> loadingCache = CacheBuilder
                .newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .recordStats()
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return service.get(s);
                    }
                });

    }



    public static void main(String[] args) {
        service.put("docId",123);

    }
}
