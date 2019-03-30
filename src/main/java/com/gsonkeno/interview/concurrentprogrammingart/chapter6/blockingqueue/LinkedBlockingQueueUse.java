package com.gsonkeno.interview.concurrentprogrammingart.chapter6.blockingqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 链表实现的有界阻塞队列
 * 线程安全
 * @author gaosong
 * @since 2019-03-30
 */
public class LinkedBlockingQueueUse {

    public static void main(String[] args) throws InterruptedException {
        //默认和最大队列长度都是MAX_VALUE
        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>(100);

        for (int i = 0; i <101 ; i++) {
            System.out.println(lbq.offer(i));
        }

        //返回队列首部元素，但不移出
        System.out.println(lbq.peek());
        System.out.println(lbq.peek());
        System.out.println(lbq.peek());

        //返回队列首部元素，并移出
        System.out.println(lbq.poll());
        System.out.println(lbq.poll());

        lbq.put(101);
        lbq.put(102);
        //队列已满，再put时会阻塞；相似地，队列已空，再take时也会阻塞
        lbq.put(103);
    }
}
