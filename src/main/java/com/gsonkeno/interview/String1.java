package com.gsonkeno.interview;

public class String1 {

    public static void main(String[] args) {
        String str0 = "123";
        String str1 = "123";
        System.out.println(str0 == str1);

        String str2 = new String("234");
        String str3 = new String("234");
        // false
        System.out.println(str2 == str3);
    }
}
