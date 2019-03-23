package com.gsonkeno.interview.jvm;



import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用
 * -Xms5m -Xmx10m  -XX:+HeapDumpOnOutOfMemoryError 时抛出
 *  Exception in thread "Thread-0" java.lang.OutOfMemoryError: GC overhead limit exceeded
 *
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
                while (true){
                    try {
                        Thread.sleep(1);
                        //被垃圾回收器回收则返回null
//                        System.out.println(wf.get());
//                        //返回是否被垃圾回收器标记为即将回收的垃圾
//                        System.out.println(wf.isEnqueued());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 1000000000; i++) {

                try{
                    list.add(new String(i + ""));
                    //Thread.sleep(1);
                }catch (Exception e){
                    System.out.println("发生异常");
                }
            }
        }).start();
    }
}
