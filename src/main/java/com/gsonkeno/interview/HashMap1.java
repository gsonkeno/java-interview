package com.gsonkeno.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * jdk7扩容时都可能导致死锁
 * jdk8在PutTreeValue时可能死循环   死循环在hashMap的1816行， java version "1.8.0_111"
 * jstack发现可能卡在 at java.util.HashMap$TreeNode.balanceInsertion(HashMap.java:2229)
 * 也有可能卡在 at java.util.HashMap$TreeNode.balanceInsertion(HashMap.java:2229)
 * @author gaosong
 * @since 2019-02-23
 */
public class HashMap1 {
    public static void main(String[] args) {
        HashMapThread hmt0 = new HashMapThread();
        HashMapThread hmt1 = new HashMapThread();
        HashMapThread hmt2 = new HashMapThread();
        HashMapThread hmt3 = new HashMapThread();
        HashMapThread hmt4 = new HashMapThread();
        hmt0.start();
        hmt1.start();
        hmt2.start();
        hmt3.start();
        hmt4.start();
    }
}

class HashMapThread extends Thread
{
    private static AtomicInteger ai = new AtomicInteger(0);
    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束完");
    }
}
