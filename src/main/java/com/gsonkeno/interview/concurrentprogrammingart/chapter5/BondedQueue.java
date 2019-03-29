package com.gsonkeno.interview.concurrentprogrammingart.chapter5;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列
 * @param <T>
 */
public class BondedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标，数组当前元素数量
    private int addIndex, remIndex, count;

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BondedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();

        try {
            while (count == items.length) {
                //当数组已满，则“未满条件notFull"没有达到，所以notFull需要等待
                notFull.await();
            }
            items[addIndex] = t;
            // ++a才会使a变大
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            count++;
            //如果之前数组为空，则remove()方法的线程可能在等待，所以这里唤醒
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T rem() throws InterruptedException {
        lock.lock();
        try {
            //使用while，而不是使用if的原因是为了防止过早或意外的通知，比如其他一个不相干的
            // 线程如果做了notifyAll就退出条件是不好的。与等待/通知范式，非常相似
            while (count == 0){
                //当数组为空，则“未空条件notEmpty”没有达到，所以notEmpty需要等待
                notEmpty.await();
            }

            Object x = items[remIndex];
            if (++remIndex == items.length){
                remIndex = 0;
            }
            count--;
            //有可能再移除元素之前，数组已经满了，这里移除了一个元素，
            //使"未满条件notFull"达成，所以需要唤醒
            notFull.signalAll();;
            return (T)x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BondedQueue<Integer> bondedQueue = new BondedQueue<>(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    try {
                        bondedQueue.add(i);
                        System.out.println("增加" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(new Random().nextInt(10)*100);
                        System.out.println("移除" + bondedQueue.rem());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
