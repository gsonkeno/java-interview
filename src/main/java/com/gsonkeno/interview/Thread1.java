package com.gsonkeno.interview;

/**
 * https://www.cnblogs.com/xrq730/p/4856361.html
 * @author gaosong
 * @since 2019-02-25
 */
public class Thread1 {

    public static void main(String[] args)
    {
        // 测试当前线程是否已经中断，执行后具有将状态标识清除为false的功能。
        // 换句话说，如果连续两次调用该方法，那么返回的必定是false：
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？" + Thread.interrupted());
        System.out.println("是否停止2？" + Thread.interrupted());
        System.out.println("end!");
    }
}
