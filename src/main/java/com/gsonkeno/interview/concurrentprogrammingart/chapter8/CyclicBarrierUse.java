package com.gsonkeno.interview.concurrentprogrammingart.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**循环屏障使用
 * Created by gaosong on 2017-07-11.
 */
public class CyclicBarrierUse {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("全部线程执行到一半在此处汇聚，不许单独行动");
            }
        });

        for (int i = 0; i < 7; i++) {
            new Worker(barrier).start();
        }

    }

    static class Worker extends  Thread{
        private  CyclicBarrier barrier;

        public Worker(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":汇聚前doSomething");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":汇聚后doSomething");
        }
    }
}
