package com.gsonkeno.applejuice;

import com.gsonkeno.appleI.Apple;
import com.gsonkeno.appleI.AppleDriver;

public class AppleJuiceDriver implements AppleDriver {
    @Override
    public void process(Apple apple) {
        System.out.println("将苹果做成苹果汁，更解渴");
    }
}
