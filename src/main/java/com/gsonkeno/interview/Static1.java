package com.gsonkeno.interview;

public  class Static1 {


    static {
        c = 3;
        //编译失败
        //System.out.println(c);
        //编译成功
        System.out.println(Static1.c);
    }

    private static int c;
}
