package org.com.zlk.cache;

import redis.clients.jedis.Jedis;

public class JedisMSTest {

    public static void main(String[] args) {
        Jedis jedisSlave2 = new Jedis("192.168.1.103",6381);
        System.out.println(jedisSlave2.ping());
        Jedis jedisSlave1 = new Jedis("192.168.1.104",6380);
        System.out.println(jedisSlave1.ping());
        Jedis jedisMaster= new Jedis("192.168.1.108",6379);
        System.out.println(jedisMaster.ping());

        jedisSlave1.slaveof("192.168.1.108",6379);

        jedisMaster.set("zlk","zlk");

        String zlk1 = jedisSlave1.get("zlk");
        String zlk2 = jedisSlave2.get("zlk");
        System.out.println(zlk1);
        System.out.println(zlk2);


    }
}
