package com.gsonkeno.interview.concurrentprogrammingart.chapter7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组
 */
public class AtomicIntegerArrayUse {

    public static void main(String[] args) {
        int[] a  = {1,2};
        AtomicIntegerArray aia = new AtomicIntegerArray(a);

        //原子更新数组时，底层的数组不会变化，原子数组对底层数组
        //做了一次元素拷贝，改变的是原子数组中的元素
        aia.compareAndSet(0,1,3);
        System.out.println(a[0]);
        System.out.println(aia.get(0));
    }
}
