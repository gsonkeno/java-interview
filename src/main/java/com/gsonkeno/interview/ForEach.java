package com.gsonkeno.interview;
/**
 * https://blog.csdn.net/wangjun5159/article/details/61415263
 * @author gaosong
 * @since 2019-02-24
 */
public class ForEach {


    /**
     * 查看字节码文件，发现对数组进行foreach，其实是数组遍历
     * @param args
     */
    public static void main(String[] args) {
        String[] arr = {"1","2"};
        for(String e : arr){
            System.out.println(e);
        }
    }
}
