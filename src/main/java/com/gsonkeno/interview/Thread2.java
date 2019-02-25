package com.gsonkeno.interview;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * https://www.cnblogs.com/xrq730/p/4853578.html
 * @author gaosong
 * @since 2019-02-25
 */
public class Thread2 {
    public static void main(String[] args) {
        try
        {
            ThreadDomain29 td = new ThreadDomain29();
            MyThread29[] mt = new MyThread29[5];
            for (int i = 0; i < mt.length; i++)
            {
                mt[i] = new MyThread29(td);
            }
            for (int i = 0; i < mt.length; i++)
            {
                mt[i].start();
            }
            Thread.sleep(1000);
            System.out.println(ThreadDomain29.aiRef.get());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

class ThreadDomain29
{
    public static AtomicInteger aiRef = new AtomicInteger();

    //加synchronized关键字与不加的区别
    public synchronized  void addNum()
    {
        System.out.println(Thread.currentThread().getName() + "加了100之后的结果：" +
                aiRef.addAndGet(100));
        aiRef.getAndAdd(1);
    }
}

class MyThread29 extends Thread
{
    private ThreadDomain29 td;

    public MyThread29(ThreadDomain29 td)
    {
        this.td = td;
    }

    public void run()
    {
        td.addNum();
    }
}
