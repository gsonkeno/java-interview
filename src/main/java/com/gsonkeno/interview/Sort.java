package com.gsonkeno.interview;

public class Sort {

    public static int minRunLength(int n) {
        assert n >= 0;
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= 32) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

    public static void main(String[] args) {
        System.out.println(minRunLength(64));
        System.out.println(minRunLength(63));
        System.out.println(minRunLength(62));
    }
}
