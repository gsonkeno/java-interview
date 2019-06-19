package com.gsonkeno.interview.understandJvm.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 *  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./java_pid.hprof来显示指定路径
 *  本包代码摘自深入理解java虚拟机
 * @author gaosong
 * @since 2019-03-23
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                }
            }
        }).start();
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }


    }
}
