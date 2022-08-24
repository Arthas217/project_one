package org.com.zlk.basic.cache;


import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  咖啡因(没明白）
 * https://juejin.cn/post/6991751225125371911
 * @Date 2022/8/22 17:30
 */
public class CaffeineCache {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    /**
     * 手动创建缓存
     */
    private static void test1() {
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(10)
                //最大条数
                .maximumSize(10)
                //PS：expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
                //最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> { })
                //记录命中
                .recordStats()
                .build();

        cache.put("1","张三");
        System.out.println(cache.getIfPresent("1"));
        System.out.println(cache.get("2",o -> "默认值"));
    }

    /**
     * 自动创建缓存
     */
    private static void test2() {
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值
                .build(key -> new Date().toString());
    }



    /**
     * 异步获取缓存
     */
    private static void test3() {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

        //异步缓存返回的是CompletableFuture
        CompletableFuture<String> future = asyncLoadingCache.get("1");
        future.thenAccept(System.out::println);
        //可以使用.executor()自定义线程池
    }


}
