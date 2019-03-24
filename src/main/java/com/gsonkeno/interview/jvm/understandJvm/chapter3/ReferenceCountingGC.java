package com.gsonkeno.interview.jvm.understandJvm.chapter3;
/**
 * 引用计数法
 * 相互引用的对象，也会被标记为垃圾对象进行回收
 * -Xms10m -Xmx10m
 * -XX:+PrintGC
 * @author gaosong
 * @since 2019-03-24
 */
public class ReferenceCountingGC {

    private Object instance = null;

    private static final int _1MB = 1024 *1024;

    //占点内存，一遍能在GC日志中看清楚是否被回收过
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
