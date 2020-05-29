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

        // 主从复制
//        config.useMasterSlaveServers().setMasterAddress("redis://192.168.1.108:6379")
//                .addSlaveAddress("redis://192.168.1.104:6380")
//                .addSlaveAddress("redis://192.168.1.103:6381");

        // 集群模式-前提是集群可用
//        config.useClusterServers().addNodeAddress(
//                "redis://192.168.1.108:7000","redis://192.168.1.108:7001",
//                "redis://192.168.1.104:7002","redis://192.168.1.104:7003",
//                "redis://192.168.1.103:7004","redis://192.168.1.103:7005");


        // Redisson在3.9.x版本中的bug
        // 注意哨兵模式配置中的端口号是26379
        config.useSentinelServers()
                .addSentinelAddress("redis://192.168.1.108:26379")
                .setMasterName("mymaster");

        // 返回的是Redisson instance
        RedissonClient redisson = Redisson.create(config);

        // 通过Redisson instance返回RLock对象实例
        RLock lock1 = redisson.getLock("redlock");
        RLock lock2 = redisson.getLock("redlock");
        RLock lock3 = redisson.getLock("redlock");

        // RedissonRedLock对象,将多个RLock对象关联为一个红锁
        // 同时加锁：lock1 lock2 lock3
        // 红锁在大部分节点上加锁成功就算成功。
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);

        boolean isLocked = false;
        try {
//            redLock.lock(1, TimeUnit.SECONDS);
            redLock.tryLock(1, 10, TimeUnit.MILLISECONDS);
            isLocked = true;
            System.out.println("-------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isLocked = false;
            redLock.unlock();
            System.out.println("------------------111111111");
        }
    }
}
