package com.gsonkeno.interview.concurrentprogrammingart.chapter7;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gaosong on 2017-11-30
 * 底层运用了硬件的CAS特性
 */
public class AtomicIntegerUse {
    public static void main(String[] args) {

        AtomicInteger ai = new AtomicInteger();
        System.out.println(ai.get()); //0

        //比较有趣的是总有先获取再操作，和先操作再获取的相对应的api操作
        System.out.println(ai.getAndIncrement());//0
        System.out.println(ai.incrementAndGet());//2

        System.out.println(ai.getAndAdd(2));//2
        System.out.println(ai.addAndGet(2));//6
    }
}
