package org.com.zlk.lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissionTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSentinelServers()
                .addSentinelAddress("127.0.0.1:6369", "127.0.0.1:6379", "127.0.0.1:6389")
                .setMasterName("masterName")
                .setPassword("password")
                .setDatabase(0);
        RedissonClient redissonClient = Redisson.create(config);
        RLock redlock1 = redissonClient.getLock("redlock");
        RLock redlock2 = redissonClient.getLock("redlock");
        RLock redlock3 = redissonClient.getLock("redlock");

        RedissonRedLock redLock = new RedissonRedLock (redlock1, redlock2, redlock3);
        boolean isLocked = false;
        try {
            redLock.lock(1, TimeUnit.SECONDS);
            redLock.tryLock(1,TimeUnit.SECONDS);
            isLocked = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isLocked =false;
            redLock.unlock();
        }


    }
}
