package com.xin.order.utils;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xinlei
 * @date 2024/12/16
 */
@Component
public class RedissonLock {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁
     *
     * @param lockKey key
     * @return 是否加锁成功
     */
    public boolean addLock(String lockKey) {
        try {
            if (redissonClient == null) {
                System.out.println("redisson client is null");
                return false;
            }
            RLock lock = redissonClient.getLock(lockKey);
            // 设置锁超时时间为5秒，到期自动释放
            // lock.lock(1, TimeUnit.SECONDS);
            lock.tryLock(100, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + ": 获取到锁");
            // 加锁成功
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解锁
     *
     * @param lockKey key
     * @return 是否解锁成功
     */
    public boolean releaseLock(String lockKey) {
        try {
            if (redissonClient == null) {
                System.out.println("redisson client is null");
                return false;
            }
            RLock lock = redissonClient.getLock(lockKey);
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ": 释放锁");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
