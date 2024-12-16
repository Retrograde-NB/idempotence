package com.xin.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisLockDemo {

    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(poolConfig, "192.168.150.4", 6379, 2000, "");

        Jedis jedis = jedisPool.getResource();

        String set = jedis.set("123", "123", "NX", "PX", 30000);

        System.out.println(set);
    }
}
