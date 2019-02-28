package com.gsonkeno.interview;

import java.util.PriorityQueue;
/**
 * https://www.cnblogs.com/Elliott-Su-Faith-change-our-life/p/7472265.html
 * @author gaosong
 * @since 2019-02-28
 */
public class PriorityQueue1 {

    //优先队列的作用是能保证每次取出的元素都是队列中权值最小的（Java的优先队列每次取最小元素，C++的优先队列每次取最大元素）
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(3);
        queue.add(1);
        queue.add(2);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
