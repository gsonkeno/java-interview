package com.gsonkeno.interview.concurrentprogrammingart.chapter9;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 线程池的处理任务的流程,各个参数意义 以及使用方式
 * @author gaosong
 * @since 2019-03-30
 */
public class ThreadPoolExecitorUse {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> abq = new ArrayBlockingQueue<Runnable>(5);
        //线程工厂，这里使用guava提供的builder创建，每次创建一个线程，线程名中的序号加1
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();

        //自定义的饱和策略
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("打印日志，任务r" + r + "被丢弃");
            }
        };

        //ThreadPoolExecutor内置了四种饱和策略，
        //1. DiscardPolicy 直接丢弃，实现RejectedExecutionHandler接口的方式是用空方法实现了抽象方法
        //2. DiscardOldestPolicy 丢弃最老的任务，最老的任务肯定排在队列的开头，因为它最先进入队列
        //        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        //            if (!e.isShutdown()) {
        //                e.getQueue().poll();  //移除队列的首部元素，即最早的任务。可以考虑下，首部空了，后面新的任务是如何再加入队列里的
        //                e.execute(r);//执行新加入的任务
        //            }
        //        }
        //3. AbortPolicy抛出异常
        //4. CallerRunsPolicy,使用当前调用者线程执行
        //           public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        //            if (!e.isShutdown()) {
        //                r.run();
        //            }
        //        }



        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,
                5, TimeUnit.SECONDS,abq,factory,handler);

        Runnable job = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "进入任务");
                    Thread.sleep(20*1000);
                    System.out.println(Thread.currentThread().getName() + "结束任务");
                    System.out.println("time:" +new Date());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            System.out.println("time:" +new Date());
            for (int i = 0; i < 10; i++) {
                pool.execute(job);
            }

            //因为任务队列的长度为5，所以前5个任务跑完需要20s，后5个任务跑完需要20s，总共需要40s
            //5s内没有任务，所以活着的5个worker转化为2个(核心线程数)
            //再等5s，提交下面这个任务时，可以debug观察到只有两个worker了
            Thread.sleep(50*1000);
            pool.execute(job);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
