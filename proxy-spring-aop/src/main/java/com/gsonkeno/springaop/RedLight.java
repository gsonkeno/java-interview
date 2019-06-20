package com.gsonkeno.springaop;

/**
 * Created by gaosong on 2017-12-01
 */
public class RedLight implements Light{
    @Override
    public void disPlayAtmosphere() {
        System.out.println("红灯光给人兴奋的感觉");
    }

    @Override
    public String getLightName() {
        return "红灯";
    }


}
