package com.gsonkeno.interview.jvm.understandJvm.chapter3;
/**
 * https://segmentfault.com/a/1190000010648021
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author gaosong
 * @since 2019-03-26
 */
public class MinorGC {

    private static final int _1MB=1024*1024;

    public static void main(String[] args) throws InterruptedException {

        long a = 1;
        long b =2;
        byte[] a1 = new byte[2*_1MB];
        byte[] a2 = new byte[2*_1MB];
        byte[] a3 = new byte[2*_1MB];
        byte[] a4 = new byte[4*_1MB];

        //Thread.sleep(Integer.MAX_VALUE);

    }
}
