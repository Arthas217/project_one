package org.com.zlk.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * 通俗点讲，watch命令就是标记一个键，如果标记了一个键，在提交事务前如果该键被别人修改过，那事务就会失败，这种情况通常可以在程序中重新再尝试一次。
 * 首先标记了键balance，然后检查余额是否足够，不足就取消标记unwatch，并不做扣减；足够的话，就启动事务进行更新操作，
 * 如果在此期间键balance被其它人修改， 那在提交事务（执行exec）时就会报错，程序中通常可以捕获这类错误再重新执行一次，直到成功。
 */
public class JedisTXTest {

    private static int balance;// 可用余额
    private static int debt;// 欠额
    private static int amtToSubtract = 40;// 实刷额度


    public static void main(String[] args) throws InterruptedException {

        Jedis jedis = new Jedis("192.168.1.103", 6381);

        // 测试联通行
        System.out.println(jedis.ping());

        //模拟db中热点数据
//        jedis.set("k1","v1");
//        jedis.set("k2","v2");
//        jedis.set("k3","v3");

        // API中 redis五大类型的测试....

        // 加锁watch监控balance
        jedis.watch("balance");

        //模拟其他程序已经修改了该条目
//        jedis.set("balance","5");
//        jedis.set("debt","95");
        // 模拟高并发延迟中
        Thread.sleep(3000);
        balance = Integer.parseInt(jedis.get("balance"));
        if (balance < amtToSubtract) {
            jedis.unwatch();
            System.out.println("modify");
            System.out.println("balance:" + jedis.get("balance"));
            System.out.println("debt:" + jedis.get("debt"));
        } else {
            System.out.println("***********transaction");
            // 事务开启
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);

            transaction.exec();
//            transaction.discard();

            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            System.out.println("*******" + balance);
            System.out.println("*******" + debt);

        }

        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("balance:" + jedis.get("balance"));
        System.out.println("debt:" + jedis.get("debt"));
    }
}
