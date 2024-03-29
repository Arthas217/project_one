package org.com.zlk.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {

    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    public static JedisPool getPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
//                    poolConfig.setMaxActive(1000);
                    poolConfig.setMaxIdle(32);
//                    poolConfig.setMaxWait(100 * 1000);
                    poolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(poolConfig, "192.168.1.108", 6379);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (null != jedis) {
            jedisPool.returnResourceObject(jedis);
        }
    }

    public static void main(String[] args) {
        //pool
        JedisPool poolInstance = getPoolInstance();
        // jedis
        Jedis jedis = poolInstance.getResource();
        jedis.set("k1", "v1");
        System.out.println(jedis.get("k1"));
        // 释放jedis
        release(poolInstance, jedis);
    }
}
