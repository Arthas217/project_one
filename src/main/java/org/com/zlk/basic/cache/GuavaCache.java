package org.com.zlk.basic.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.*;

public class GuavaCache {

    private static ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();

    // Guava Cache的使用
    private static LoadingCache<String, Integer> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(0, TimeUnit.SECONDS)
            .refreshAfterWrite(0, TimeUnit.SECONDS)
            .recordStats()
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String s) throws Exception {
                    return 0;
                }
            });

    public static void main(String[] args) throws ExecutionException {
        loadingCache.get("key");
        loadingCache.refresh("key");
    }
}
