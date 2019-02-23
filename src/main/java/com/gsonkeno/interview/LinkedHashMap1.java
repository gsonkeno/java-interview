package com.gsonkeno.interview;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMap1 {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1","1");
        map.put("3","3");
        map.put("2","2");

        //按插入顺序打印
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        LinkedHashMap<String, String> map1 = new LinkedHashMap<String, String>(16,0.75f,true);
        map1.put("1","1");
        map1.put("3","3");
        map1.put("2","2");

        //1在头，2在尾
        //按访问顺序打印
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //3被访问，移到尾部
        map1.get("3");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
