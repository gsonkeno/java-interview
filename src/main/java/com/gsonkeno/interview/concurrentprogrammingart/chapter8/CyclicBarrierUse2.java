package com.gsonkeno.interview.concurrentprogrammingart.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**循环屏障使用
 * Created by gaosong on 2017-07-11.
 */
public class CyclicBarrierUse2 {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    System.out.println("t线程interrupt");
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    System.out.println("t线程broken");
                    e.printStackTrace();
                }
            }
        });

        t.start();
        t.interrupt();

        try {
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println("主线程interrupt");
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("主线程broken");

            e.printStackTrace();
            System.out.println(barrier.isBroken());
        }
    }
}
