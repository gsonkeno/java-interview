package com.gsonkeno.interview.concurrentprogrammingart.chapter6.blockingqueue;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * 不存储元素的阻塞队列 传球手
 *
 * 真的是不存储元素，调用add抛异常，调用offer返回false，真的是一个元素都不存储
 * @author gaosong
 * @since 2019-03-30
 */
public class SynchronousQueueUse {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<Integer> sq = new SynchronousQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    try {
                        sq.put(i);
                        Thread.sleep(new Random().nextInt(10) *100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(sq.take());
                        Thread.sleep(new Random().nextInt(10)*100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
