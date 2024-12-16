package com.xin.zookeeper;

import com.xin.zookeeper.lock.AbstractLock;
import com.xin.zookeeper.lock.HighLock;
import com.xin.zookeeper.lock.LowLock;

import java.util.concurrent.TimeUnit;

/**
 * @author xinlei
 * @date 2024/12/13
 */
public class LockTest {
    public static void main(String[] args) {
        // 模拟多个10个客户端
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new LockRunnable());
            thread.start();
        }
    }

    private static class LockRunnable implements Runnable {
        @Override
        public void run() {
            // AbstractLock abstractLock = new LowLock();
            AbstractLock abstractLock = new HighLock("/high_lock");
            abstractLock.getLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            abstractLock.releaseLock();
        }
    }
}
