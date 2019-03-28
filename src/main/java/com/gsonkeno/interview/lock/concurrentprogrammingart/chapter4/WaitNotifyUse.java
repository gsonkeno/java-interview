package com.gsonkeno.interview.lock.concurrentprogrammingart.chapter4;

import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * https://blog.csdn.net/leel0330/article/details/80455307
 * 1.永远在synchronized的函数或对象里使用wait、notify和notifyAll，
 * 不然Java虚拟机会生成IllegalMonitorStateException。
 *
 * 2.永远在while循环里而不是if语句下使用wait。这样，
 * 循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知。
 *
 * 3.永远在多线程间共享的对象上使用wait。
 *
 * 4.notify随机通知一个阻塞在对象上的线程；notifyAll通知阻塞在对象上所有的线程。
 * @author gaosong
 * @since 2019/3/28
 */
public class WaitNotifyUse {
    private static AtomicInteger capacity = new AtomicInteger(0);
    private static Object o = new Object();

    static class ProduceThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (o){
                    while (capacity.get() >=20){
                        try {
                            System.out.println("生产线程等待");
                            o.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    int i = capacity.addAndGet(1);
                    System.out.println("生产，容器元素个数:" + i);
                    o.notifyAll();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class ConsumeThread implements Runnable {
        @Override
        public void run() {

            while (true) {
                synchronized (o){
                    //低于10不消费，等待
                    while (capacity.get() < 10){
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    int i = capacity.addAndGet(-1);
                    System.out.println("消费，容器元素个数:" + i);
                    o.notifyAll();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ConsumeThread()).start();
        new Thread(new ConsumeThread()).start();

        new Thread(new ProduceThread()).start();
    }
}
