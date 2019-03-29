package com.gsonkeno.interview.concurrentprogrammingart.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author gaosong
 * @since 2019/3/29
 */
public class ConditionUseCase {
    static Lock      lock      = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始await");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "从await恢复");
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始signal");
            condition.signal();
        } finally {
            System.out.println(Thread.currentThread().getName() + "开始unlock");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionUseCase conditionUseCase = new ConditionUseCase();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    conditionUseCase.conditionSignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
