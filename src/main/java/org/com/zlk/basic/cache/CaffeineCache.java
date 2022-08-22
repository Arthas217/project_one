package org.com.zlk.basic.cache;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description:  咖啡因
 * https://juejin.cn/post/6991751225125371911
 * @Date 2022/8/22 17:30
 */
public class CaffeineCache {

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

    public static void main(String[] args) {
        test1();
    }

}
