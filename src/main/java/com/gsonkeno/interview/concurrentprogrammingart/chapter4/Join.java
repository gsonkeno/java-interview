package com.gsonkeno.interview.concurrentprogrammingart.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * 在线程A的上下文环境中调用thread.join()方法时，会导致thread线程运行结束
 * 后，再继续执行线程A上下文中的后续代码
 *j
 * Created by gaosong on 2017-08-24.
 */
public class Join {

    /***
     * 此示例中10个线程在执行时，，需要等待前一个线程执行完；
     * 比如线程0要等待main线程执行完
     * 线程9要等到线程8执行完
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Dimon(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "terminate");
    }

    static class Dimon implements Runnable{
        private Thread thread;

        public Dimon(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            }catch (Exception e){

            }
            System.out.println( Thread.currentThread().getName() + ":terminate" );
        }
    }
}
