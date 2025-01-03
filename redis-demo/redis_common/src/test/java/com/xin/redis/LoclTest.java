package com.xin.redis;

import com.xin.redis.util.SingleRedisLock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author xinlei
 * @date 2024/12/16
 */
public class LoclTest {
    public static void main(String[] args) {
        // 模拟多个5个客户端
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new LockRunnable());
            thread.start();
        }
    }

    private static class LockRunnable implements Runnable {
        @Override
        public void run() {
            SingleRedisLock singleRedisLock = new SingleRedisLock();
            String requestId = UUID.randomUUID().toString();
            boolean lockResult = singleRedisLock.tryLock("lock", requestId);
            if (lockResult) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            singleRedisLock.releaseLock("lock", requestId);
        }
    }
}
