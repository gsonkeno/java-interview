package com.gsonkeno.interview;
/**
 * https://www.cnblogs.com/xrq730/p/4851344.html
 * 线程类的构造方法、静态块是被main线程调用的，而线程类的run()方法才是应用线程自己调用的
 * @author gaosong
 * @since 2019-02-25
 */
public class Thread5 extends Thread
{
    public Thread5()
    {
        System.out.println("MyThread5----->Begin");
        System.out.println("Thread.currentThread().getName()----->" +
                Thread.currentThread().getName());
        System.out.println("this.getName()----->" + this.getName());
        System.out.println("MyThread5----->end");
    }

    @Override
    public void run()
    {
        System.out.println("run----->Begin");
        System.out.println("Thread.currentThread().getName()----->" +
                Thread.currentThread().getName());
        System.out.println("this.getName()----->" + this.getName());
        System.out.println("run----->end");
    }

    public static void main(String[] args) {
        Thread5 mt5 = new Thread5();
        mt5.start();
    }

}
