package com.gsonkeno.interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * https://www.cnblogs.com/xrq730/p/5020760.html
 */
public class List2 {

    static class T1 extends Thread
    {
        private List<Integer> list;

        public T1(List<Integer> list)
        {
            this.list = list;
        }

        @Override
        public void run()
        {
            for (Integer i : list)
            {
            }
        }
    }

    static class T2 extends Thread
    {
        private List<Integer> list;

        public T2(List<Integer> list)
        {
            this.list = list;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < list.size(); i++)
            {
                list.remove(i);
            }

            Iterator<Integer> iterator = list.iterator();
//            while (iterator.hasNext()){
//                iterator.next();
//                iterator.remove();
//
//            }
        }
    }



    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < 10000; i++)
        {
            list.add(i);
        }

        T1 t1 = new T1(list);
        T2 t2 = new T2(list);
        t1.start();
        t2.start();
    }
}
