package com.gsonkeno.interview.concurrentprogrammingart.chapter4;

/**
 * 等待通知机制
 * <li>wait-notify机制需要与锁机制配合使用(synchronized)</li>
 * <li>wait方法会放弃锁</li>
 * Created by gaosong on 2017-12-01
 */
public class WaitNotifyUse2 {

    static boolean condionSatisfy; //条件是否满足，若满足，则wait线程可以继续执行；否则，wait线程需要阻塞

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new WaitThread(),"等待线程").start();
        Thread.sleep(1);
        new Thread(new NotifyThread(),"通知线程").start();
    }
//    Thread[等待线程,5,main]获取到锁，但条件不满足,即将放弃锁,wait阻塞
//    Thread[通知线程,5,main]获取到锁，并使条件满足,notify
//    Thread[通知线程,5,main]即将放弃锁
//    Thread[通知线程,5,main]再次获取到锁
//    Thread[等待线程,5,main]再次获取到锁，从wait阻塞处返回

    static class WaitThread implements Runnable{

        @Override
        public void run() {
            while (!condionSatisfy){//条件不满足，不向下执行

                synchronized (lock){
                    try {
                        System.out.println(Thread.currentThread() + "获取到锁，但条件不满足," +
                                "即将放弃锁,wait阻塞");
                        lock.wait();
                        System.out.println(Thread.currentThread() + "再次获取到锁，从wait阻塞处返回");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    static class NotifyThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + "获取到锁，并使条件满足,notify");
                condionSatisfy = true;
                lock.notifyAll();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread() + "即将放弃锁");
            }

            //刚放弃锁的线程再次获取锁更加容易
            synchronized (lock){
                System.out.println(Thread.currentThread() + "再次获取到锁");
            }
        }
    }
}
