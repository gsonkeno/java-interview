package com.gsonkeno.interview.algo;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilter1 {

    public static void main(String[] args) {
        //预计要插入多少数据
        int size = 100_0000;

        //期望的误判率
        float fpp = 0.01f;

        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
        for (int i = 0; i < size; i++) {
            filter.put(i);
        }
        int count = 0;
        for (int i = 100_0000; i < 200_0000 ; i++) {
            if (filter.mightContain(i)){
                count ++;
                System.out.println(i + "被误判了");
            }
        }

        System.out.println("总共的误判数" + count);
    }
}
