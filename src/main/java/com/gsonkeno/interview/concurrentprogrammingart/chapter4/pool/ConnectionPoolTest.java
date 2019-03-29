package com.gsonkeno.interview.concurrentprogrammingart.chapter4.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gaosong on 2017-09-01
 */
public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 100;
        end = new CountDownLatch(threadCount);

        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notgot = new AtomicInteger();
        for (int i =0; i < threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notgot),
                    "connectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke:" + (threadCount * count));
        System.out.println("got connection:" + got);
        System.out.println("notgot connection:" + notgot);

    }

    static class  ConnectionRunner implements  Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notgot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notgot) {

            this.count = count;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count > 0){
                try {
                    Connection connection = pool.fetchConnection(1000);

                    if (connection != null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notgot.incrementAndGet();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count --;
                }
            }
            end.countDown();
        }
    }

}
