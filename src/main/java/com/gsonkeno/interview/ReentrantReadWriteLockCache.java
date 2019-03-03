package com.gsonkeno.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author gaosong
 * @since 2019-03-03
 */
public class ReentrantReadWriteLockCache {
    private static Map<String, Object> map = new HashMap<>();
    private static ReentrantReadWriteLock readWriteLock = new
            ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private static Object get(String key) {
        readLock.lock();
        Object result = map.get(key);
        readLock.unlock();
        return result;
    }

    private static void put(String key, Object value) {
        writeLock.lock();
        map.put(key, value);
        writeLock.unlock();
    }

    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.execute(() -> {
                put(index + "", index);
                System.out.println(Thread.currentThread().getName() + " put " + index);
            });
        }

        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.execute(() -> {
                get(index + "");
                System.out.println(Thread.currentThread().getName() + " get " + index);
            });

        }

        executorService.shutdown();
    }
}
