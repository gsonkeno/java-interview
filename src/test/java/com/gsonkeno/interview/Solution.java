package com.gsonkeno.interview;

import java.io.*;
import java.util.*;

public class Solution {

    public void handleShell(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String fileLine;
        List<String> content = new ArrayList<>();
        while ((fileLine = br.readLine()) != null) {
            content.add(fileLine);
        }


        //hashMap统计次数
        Map<String,Integer> map = new HashMap<>();
        for( String line : content){
            if (line.contains("Exception")){
                map.put(line, map.getOrDefault(line,0) + 1);
            }
        }

        System.out.println(map);

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                int result = o2.getValue().compareTo(o1.getValue());
                 if (result == 0){
                    return o2.getKey().compareTo(o1.getKey());
                }else {
                     return result;
                 }
            }
        });
        int i =0;
        for(Map.Entry<String,Integer> mapping:list){

            System.out.println(mapping.getValue()+" "+ mapping.getKey());
            if (++i>=10){
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\test.txt");
        new Solution().handleShell(file);
    }
}
