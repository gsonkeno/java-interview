package com.gsonkeno.interview.jvm.understandJvm.chapter3;

/**
 * -verbose:gc -Xms40m -Xmx40m -Xmn20m -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:PretenureSizeThreshold=3145728
 * PretenureSizeThreshold参数只对 Serial和 ParNew两款收集器有效，
 * PS收集器并不认识这个参数
 *
 * 大对象优先进入老年代
 * @author gaosong
 * @since 2019/3/26
 */
public class PretenureSizeThreshold {

    public static void main(String[] args) {
        int a = 1;
        byte[] a1 = new byte[5*1024*1024];
        int b = 2;
    }
}
