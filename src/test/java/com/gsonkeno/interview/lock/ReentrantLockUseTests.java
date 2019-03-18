package com.gsonkeno.interview.lock;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReentrantLockUseTests {

    @Test
    public void testLock() throws InterruptedException {
        int nums = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(nums);

        //多线程同时竞争可重入锁，需要排队
        ReentrantLockUse reentrantLockUse = new ReentrantLockUse();
        for (int i = 0; i < nums; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    reentrantLockUse.lock();
                }
            });
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void testLockInterrupt() {
        ReentrantLockUse reentrantLockUse = new ReentrantLockUse();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockUse.lockInterrupt();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockUse.lockInterrupt();
            }
        });

        //thread1获取到锁
        thread1.start();
        //thread2需要等待
        thread2.start();
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
        }

        //使线程thread1,thread2发生中断
        //thread1没有任何影响，因为其已经获取到锁，对中断不敏感
        //thread2受影响，因为它没有获取到锁，在等待队列上，对中断敏感，会及时响应中断
        thread1.interrupt();
        thread2.interrupt();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {

        }
    }

}
