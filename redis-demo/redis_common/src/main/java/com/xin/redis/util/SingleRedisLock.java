package com.xin.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @author xinlei
 * @date 2024/12/16
 */
public class SingleRedisLock {
    JedisPoolConfig poolConfig = new JedisPoolConfig();

    JedisPool jedisPool = new JedisPool(poolConfig, "192.168.150.4", 6379, 2000, "");

    // 锁过期时间
    protected long internalLockLeaseTime = 30000;
    // 获取锁的超时时间
    private long timeout = 999999;

    SetParams setParams = SetParams.setParams().nx().px(internalLockLeaseTime);

    /**
     * 加锁
     *
     * @param lockKey   锁键
     * @param requestId 请求唯一标识
     * @return 是否加锁成功
     */
    public boolean tryLock(String lockKey, String requestId) {
        String threadName = Thread.currentThread().getName();
        try (Jedis jedis = this.jedisPool.getResource()) {
            Long start = System.currentTimeMillis();
            for (; ; ) {
                String lockResult = jedis.set(lockKey, requestId, setParams);
                if ("OK".equals(lockResult)) {
                    System.out.println(threadName + ": 获取锁成功");
                    return true;
                }
                // 否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                System.out.println(threadName + ": 获取锁失败，等待中");
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解锁
     *
     * @param lockKey   锁键
     * @param requestId 请求唯一标识
     * @return 是否解锁成功
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String threadName =
                Thread.currentThread().getName();
        System.out.println(threadName + "：释放锁");
        try (Jedis jedis = this.jedisPool.getResource()) {
            String lua = "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                    " return redis.call('del',KEYS[1]) " +
                    "else" +
                    " return 0 " +
                    "end";
            Object result = jedis.eval(lua, Collections.singletonList(lockKey), Collections.singletonList(requestId));
            return "1".equals(result.toString());
        }
    }
}
