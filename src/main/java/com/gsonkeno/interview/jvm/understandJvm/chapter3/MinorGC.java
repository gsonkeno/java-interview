package com.gsonkeno.interview.jvm.understandJvm.chapter3;
/**
 *
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author gaosong
 * @since 2019-03-26
 */
public class MinorGC {

    private static final int _1MB=1024*1024;

    public static void main(String[] args) throws InterruptedException {
        byte[] a1,a2,a3,a4;
        a1 = new byte[2*_1MB];
        a2 = new byte[2*_1MB];
        //a3 = new byte[2*_1MB];
        //a4 = new byte[4*_1MB]; //出现一次Minor GC
        //Thread.sleep(Integer.MAX_VALUE);
    }
}
