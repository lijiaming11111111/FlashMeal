package com.school.flashmeal.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLockUtil {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    public boolean tryLock(String lockKey,long waitTime,long leastTime) {
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            return lock.tryLock(waitTime,leastTime,TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//            return false;
//        }
//    }
//
//    public void unlock(String lockKey){
//        RLock lock = redissonClient.getLock(lockKey);
//        if (lock.isHeldByCurrentThread()){
//            lock.unlock();
//        }
//    }
    @Autowired
    private RedissonClient redissonClient;
    //创建 ThreadLocal 实例
    private final ThreadLocal<RLock> currentLock = new ThreadLocal<>();
    //此时 ThreadLocal 是空的
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        //获取锁对象（本地操作，不访问 Redis）
        RLock lock = redissonClient.getLock(lockKey);   //创建了一个 RLock 对象，代表 "dish:lock:1" 这把锁
        try {
            //尝试获取锁（访问 Redis，可能阻塞）
            boolean acquired = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS); // 情况A：成功 → acquired = true，当前线程持有锁
                                                                                    // 情况B：失败 → acquired = false，当前线程未持有锁
            //如果成功，保存到 ThreadLocal
            if (acquired) {
                currentLock.set(lock);
            }
            return acquired;
            //如果被中断，恢复中断标志
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
            return false;
        }
    }
    public void unlock(String lockKey) {
        //从 ThreadLocal 取出锁对象
        RLock lock = currentLock.get();
        if (lock != null && lock.isHeldByCurrentThread()) {
            // 安全检查后解锁
            lock.unlock();
            currentLock.remove();
        }
    }
}
