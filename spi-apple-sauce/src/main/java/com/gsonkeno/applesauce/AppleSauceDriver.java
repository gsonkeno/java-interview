package com.gsonkeno.applesauce;

import com.gsonkeno.appleI.Apple;
import com.gsonkeno.appleI.AppleDriver;

public class AppleSauceDriver implements AppleDriver {
    @Override
    public void process(Apple apple) {
        System.out.println("将苹果做成苹果泥，更丝滑");
    }
}
