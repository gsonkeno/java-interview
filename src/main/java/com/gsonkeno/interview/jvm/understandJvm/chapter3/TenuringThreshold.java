package com.gsonkeno.interview.jvm.understandJvm.chapter3;
/**
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * @author gaosong
 * @since 2019/3/26
 */
public class TenuringThreshold {

    public static void main(String[] args) {
        byte[] a1 = new byte[1024*1024/4];
        byte[] a2 = new byte[4 * 1024*1024];
        byte[] a3 = new byte[4 * 1024*1024/4];
        a3 = null;

        byte[] a4 = new byte[4 * 1024*1024/4];
    }
}
