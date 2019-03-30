package com.gsonkeno.interview.concurrentprogrammingart.chapter6.blockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 支持优先级的无界阻塞队列
 * 线程安全
 *
 * @author gaosong
 * @since 2019-03-30
 */
public class PriorityBlockingQueueUse {

    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>();

        pbq.add(1);
        pbq.add(2);
        pbq.add(3);

        //默认情况下，按照自然排序升序排列
        System.out.println(pbq.poll());
        System.out.println(pbq.poll());
        System.out.println(pbq.poll());
    }
}
