package com.gsonkeno.interview.jvm.understandJvm.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出 使用unsafe分配本机内存
 * -XX:MaxDirectMemorySize=10M
 * @author gaosong
 * @since 2019-03-24
 */
public class DirectMemoryErrorOOM {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        //因为Unsafe类的第一个属性就是Unsafe静态实例，所以unsafeField指代的是Unsafe类
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        //因为Unsafe类的第一个属性是静态的，所以get(null)即可返回对象
        Unsafe unsafe = (Unsafe)unsafeField.get(null);

        //虽然使用DirectByteBuffer分配内存也会抛出内存溢出异常，但它抛出异常时并没有
        //真正向操作系统申请分配内存，而是通过计算得知内存无法分配,于是手动抛出异常

        //真正申请分配内存的方法是unsafe.allocateMemory()
        while (true){
            unsafe.allocateMemory(_1MB);
        }

        //Exception in thread "main" java.lang.OutOfMemoryError
    }
}
