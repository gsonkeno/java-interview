package com.gsonkeno.interview.concurrentprogrammingart.chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch，一个同步辅助类，
 * 在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待，
 * 主要方法countDown(),await()
 * countDown()方法建议在finally中使用，以免线程一直阻塞
 * Created by gaosong on 2017-07-09.
 */
public class CountDownLatchUse {

    public static  void main(String[] args) throws InterruptedException {
        int times = 50;

        CountDownLatch latch = new CountDownLatch(times);

        for (int i = 0; i < 70; i++) {
            new Consumer(latch).start();
        }

        latch.await();
        System.out.println("Thread main end");
    }

    static class Consumer extends Thread{


        private  CountDownLatch latch;

        public Consumer(CountDownLatch latch){
            this.latch = latch;

        }
        @Override
        public void run() {
            System.out.println("Thread" + Thread.currentThread().getId()
                    + ",latch" + latch.getCount());
            latch.countDown();


        }
    }


}

