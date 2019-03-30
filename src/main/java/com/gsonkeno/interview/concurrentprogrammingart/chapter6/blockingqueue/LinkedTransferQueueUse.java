package com.gsonkeno.interview.concurrentprogrammingart.chapter6.blockingqueue;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 链表实现的无界阻塞队列
 *
 * @author gaosong
 * @since 2019-03-30
 */
public class LinkedTransferQueueUse {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<Integer> ltq = new LinkedTransferQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ltq.put(0);
                    System.out.println("put 0成功");
                    ltq.transfer(1);
                    System.out.println("transfer 1成功");
                    ltq.transfer(2);
                    System.out.println("transfer 2成功");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ltq.poll());
            }
        }).start();
    }
}
