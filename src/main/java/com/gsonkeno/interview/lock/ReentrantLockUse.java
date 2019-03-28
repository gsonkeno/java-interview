package com.gsonkeno.interview.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁使用
 * @author gaosong
 * @since 2019-03-18
 */
public class ReentrantLockUse {
    private ReentrantLock lock = new ReentrantLock();

    public void lock(){
        try {
            //获取锁
            lock.lock();
            System.out.println(Thread.currentThread() + "获取到重入锁");

            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread() + "释放掉重入锁");
        }
    }

    /**中断式获取锁**/
    public void lockInterrupt(){
        try{
            //如果一个线程在获取锁的等待队列上时，对该线程执行thread.interrupt，
            //它可以及时响应中断
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread() + "获取到重入锁");
            while (true){

            }
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread() + "发生中断异常");
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread() + "释放掉重入锁");
        }
    }
}
