package com.gsonkeno.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class SortTest {

    @Test
    public void testComparator(){
        Comparator<Integer> desendingComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        };

        Integer[] a = {5,3,4,7,8,6,2,9};
        Arrays.sort(a,desendingComparator);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        System.out.println(desendingComparator.compare(a[2],a[1]));
    }
}
