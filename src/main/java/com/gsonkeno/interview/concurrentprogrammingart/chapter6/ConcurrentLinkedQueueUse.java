package com.gsonkeno.interview.concurrentprogrammingart.chapter6;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 线程安全的并发队列
 * @author gaosong
 * @since 2019-03-30
 */
public class ConcurrentLinkedQueueUse {

    public static void main(String[] args) {
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue<Integer>();

        clq.add(1);
        clq.add(2);
        clq.add(3);
        clq.add(4);
        clq.add(5);

    }
}
