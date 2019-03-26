package com.gsonkeno.interview.jvm.understandJvm.chapter7;

public class Invoker {

    /**
     * 如何使类Invoked初始化的5中情况
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
/*        String a = Invoked.a;

        Class.forName("com.gsonkeno.interview.jvm.understandJvm.chapter7.Invoked");*/

        new InvokedChild();
    }
}
