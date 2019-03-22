package com.gsonkeno.interview.jvm;



import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 强引用，软引用，弱引用，虚引用
 *
 * @author gaosong
 * @since 2019/3/22
 */
public class Reference1 {
    public static void main(String[] args) {
        Object obj = new Object();
        WeakReference<Object> wf = new WeakReference<>(obj);
        obj = null;
        List<String> list = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000000; i++) {
                    list.add(new String(i + ""));
                }
            }
        }).start();
        //有时候会返回null
        System.out.println(wf.get());
        //返回是否被垃圾回收器标记为即将回收的垃圾
        System.out.println(wf.isEnqueued());
    }
}
