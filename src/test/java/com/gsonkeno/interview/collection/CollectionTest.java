package com.gsonkeno.interview.collection;

import org.junit.Test;

import java.util.ArrayList;

public class CollectionTest {

    @Test
    public void testArrayList(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(null);
        System.out.println(arrayList.size());
    }
}
