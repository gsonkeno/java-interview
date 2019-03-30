package com.gsonkeno.interview.concurrentprogrammingart.chapter6.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 数组有界阻塞队列
 * concurrent包下的类都是支持并发访问的，所以阻塞队列都是线程安全的
 * @author gaosong
 * @since 2019-03-30
 */
public class ArrayBlockingQueueUse {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(100);

        // add方法最终会调用到offer方法，该方法的实现是通过lock加锁的，所以是线程安全的
        // add方法与offer方法的区别是，队列已满时，add方法会抛出异常;而offer方法返回false而已
        for (int i = 0; i < 101; i++) {
            // abq.add(i);
            System.out.println(abq.offer(i));
        }

    }
}
