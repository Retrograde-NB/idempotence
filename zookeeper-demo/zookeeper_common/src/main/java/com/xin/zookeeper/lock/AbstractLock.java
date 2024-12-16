package com.xin.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author xinlei
 * @date 2024/12/13
 */
public abstract class AbstractLock {
    // zookeeper服务器地址
    public static final String ZK_SERVER_ADDR = "192.168.150.4:2181";
    // zookeeper超时时间
    public static final int CONNECTION_TIME_OUT = 30000;

    public static final int SESSION_TIME_OUT = 30000;
    // 创建zk客户端
    protected ZkClient zkClient = new ZkClient(ZK_SERVER_ADDR, SESSION_TIME_OUT, CONNECTION_TIME_OUT);

    /**
     * 获取锁
     */
    public abstract boolean tryLock();

    /**
     * 等待加锁
     */
    public abstract void waitLock();

    /**
     * 释放锁
     */
    public abstract void releaseLock();

    public void getLock() {
        String threadName = Thread.currentThread().getName();
        if (tryLock()) {
            System.out.println(threadName + ": 获取锁成功");
        } else {
            System.out.println(threadName + ": 获取锁失败，等待中");
            // 等待锁
            waitLock();
            getLock();
        }
    }
}
