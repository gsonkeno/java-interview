package com.gsonkeno.interview.collection;

import org.junit.Test;

import java.util.ArrayList;

public class CollectionTest {

    @Test
    public void testArrayList(){
        //ArrayList能够添加null元素，但是线程不安全
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(null);
        System.out.println(arrayList.size());

    }
}
