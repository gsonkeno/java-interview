package com.gsonkeno.interview.concurrentprogrammingart;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by gaosong on 2017-09-14
 * Thread有6中状态: NEW, RUNNABLE(Running + ready), BLOCKED, WAITING, TIMED_WAITING, TERMINATED
 *
 * 
 */
public class ThreadUse {

    private static Object o  = new Object();




    private static class RunCase implements Runnable{
        @Override
        public void run() {
            synchronized (RunCase.class){
                while (true){
                }
            }
        }
    }
    /**
     * 两个线程几乎同时执行，任务对类对象加锁。
     * 一定有一个线程获取锁后循环不退出；另一个线程获取不到锁阻塞
     */
    @Test
    public void runnable2Blocked(){
        Thread thread1 = new Thread(new RunCase(), "RunCase1");
        Thread thread2 = new Thread(new RunCase(), "RunCase2");

        thread1.start();
        thread2.start();
        //可以通过jps和jstack看到如下栈信息

        //"RunCase2" #15 prio=5 os_prio=0 tid=0x000000001fae2000 nid=0xf44 waiting for monitor entry [0x000000002044e000]
        //   java.lang.Thread.State: BLOCKED (on object monitor)
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase.run(ThreadUse.java:33)
        //        - waiting to lock <0x000000076bf4a0d0> (a java.lang.Class for com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase)
        //        at java.lang.Thread.run(Thread.java:745)
        //
        //"RunCase1" #14 prio=5 os_prio=0 tid=0x000000001fae1800 nid=0x35bc runnable [0x000000002034f000]
        //   java.lang.Thread.State: RUNNABLE
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase.run(ThreadUse.java:33)
        //        - locked <0x000000076bf4a0d0> (a java.lang.Class for com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase)
        //        at java.lang.Thread.run(Thread.java:745)
        SleepUtil.sleep(200);
    }

    /**
     * 由运行状态转到waiting状态，调用wait
     */
    @Test
    public   void runnable2WaitByWait()  {
        //"main" #1 prio=5 os_prio=0 tid=0x0000000003343000 nid=0x3de8 in Object.wait() [0x000000000323e000]
        //   java.lang.Thread.State: WAITING (on object monitor)
        //        at java.lang.Object.wait(Native Method)
        //        - waiting on <0x000000076bf360c0> (a java.lang.Object)
        //        at java.lang.Object.wait(Object.java:502)
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse.runnable2WaitByWait(ThreadUse.java:72)
        //        - locked <0x000000076bf360c0> (a java.lang.Object)
        synchronized (o){
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 由运行状态转到waiting状态，调用join
     * RunCase线程一直在运行，不会结束；导致main线程处于waiting状态
     */
    @Test
    public  void runnable2WaitByJoin()  {
        Thread thread = new Thread(new RunCase(), "RunCase");
        thread.start();

        //"main" #1 prio=5 os_prio=0 tid=0x0000000002b92800 nid=0x1b30 in Object.wait() [0x000000000264e000]
        //   java.lang.Thread.State: WAITING (on object monitor)
        //        at java.lang.Object.wait(Native Method)
        //        - waiting on <0x000000076bce2ff0> (a java.lang.Thread)
        //        at java.lang.Thread.join(Thread.java:1249)
        //        - locked <0x000000076bce2ff0> (a java.lang.Thread)
        //        at java.lang.Thread.join(Thread.java:1323)
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse.runnable2WaitByJoin(ThreadUse.java:97)
        //        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        SleepUtil.sleep(2);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由运行状态转到waiting状态，调用LockSupport.park()
     * 该方法导致当前线程(此处是main线程)阻塞，状态变为waiting
     * 返回常见的有两种方式，unpark()或者被中断
     */
    @Test
    public  void runnable2WaitByPark()  {
        //"main" #1 prio=5 os_prio=0 tid=0x00000000030b3000 nid=0x2538 waiting on condition [0x0000000002b3e000]
        //   java.lang.Thread.State: WAITING (parking)
        //        at sun.misc.Unsafe.park(Native Method)
        //        at java.util.concurrent.locks.LockSupport.park(LockSupport.java:304)
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse.runnable2WaitByPark(ThreadUse.java:119)
        LockSupport.park();
    }

    /**
     * 测试中断线程
     * 1)处于正常运行中的线程如果被中断，只是修改了一下中断标识位
     * 2)处于阻塞状态(如wating)的线程被中断，中断标识位会复位，且会抛出异常
     *   方法上本身也声明了会抛出异常,如wait()就声明抛出中断异常
     */
    @Test
    public   void testInterrupt(){
        //Thread thread = new Thread(new RunCase(), "RunCase");

        //"BlockRunCase" #14 prio=5 os_prio=0 tid=0x000000001f907800 nid=0x25c0 in Object.wait() [0x000000001ffff000]
        //   java.lang.Thread.State: WAITING (on object monitor)
        //        at java.lang.Object.wait(Native Method)
        //        - waiting on <0x000000076bf8f918> (a java.lang.Class for com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase)
        //        at java.lang.Object.wait(Object.java:502)
        //        at com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$BlockRunCase.run(ThreadUse.java:209)
        //        - locked <0x000000076bf8f918> (a java.lang.Class for com.gsonkeno.interview.concurrentprogrammingart.ThreadUse$RunCase)
        //        at java.lang.Thread.run(Thread.java:745)
        Thread thread = new Thread(new BlockRunCase(), "BlockRunCase");
        thread.start();

        SleepUtil.sleep(20);
        thread.interrupt();
    }

    /**
     * interrupted测试当前线程是否被中断，不管其有没有被中断，中断标识位都会被清除
     *
     * 正常情况下连续调用该方法两次，第二次会返回false;
     *
     * 除非第一次调用后，第二次调用前，当前线程又被中断了，这么短的时间内
     * 发生该事件的概率是极低的
     */
    @Test
    public  void testInterrupted(){
        Thread thread = Thread.currentThread();
        thread.interrupt();
        //先中断该线程，则Thread.interrupted()返回true，
        //中断标识位被清除，再次调用，则判断为false

        Assert.assertTrue(Thread.interrupted());
        Assert.assertFalse(Thread.interrupted());
    }

    /**
     * 测试一个线程处在blocked状态时，被中断的反应
     * 不会抛出异常，只是改变状态位
     */
    @Test
    public  void testInterruptOnBlocked(){
        Thread thread1 = new Thread(new RunCase(), "RunCase1");
        Thread thread2 = new Thread(new RunCase(), "RunCase2");

        thread1.start();
        SleepUtil.sleep(2);
        //thread2处于block状态
        thread2.start();
        thread2.interrupt();
        Assert.assertTrue(thread2.isInterrupted());
        Assert.assertTrue(thread2.isInterrupted());
        Assert.assertTrue(thread2.isInterrupted());

        SleepUtil.sleep(2);
    }


    /**
     * sleep不会导致当前线程释放所占用的锁,而wait会
     *
     * sleep会导致当前线程进入timed-wating状态
     * wait导致当前线程进入wating状态
     */
    @Test
    public  void testSleep(){
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 调用wait()方法使线程处于waiting状态
     */
    @Test
    public  void testWait(){
        Thread thread = new Thread(new BlockRunCase(), "RunCase");
        thread.start();
    }



    private static class BlockRunCase implements Runnable{
        @Override
        public void run() {
            synchronized (RunCase.class){
                while (true){
                    try {
                        RunCase.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
