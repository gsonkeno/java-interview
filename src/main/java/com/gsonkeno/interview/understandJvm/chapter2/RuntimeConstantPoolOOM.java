package com.gsonkeno.interview.understandJvm.chapter2;
/**
 * String.intern()返回引用的测试
 * @author gaosong
 * @since 2019-03-24
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //jdk1.7, 1.8中返回true，因为1.7的intern实现不会再实现复制实例，只是在常量池中记录
        //首次出现的实例引用。 1.7中字面量(interned strings)转移到了java heap；
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        //jdk1.7, 1.8中返回false,jvm启动后，常量池中已经有了"java"的引用，不符合“首次出现”的规则
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        //如果是jdk1.6，则返回的都是false，因为一个在堆中，一个在永久代(jdk1.6中hotspot虚拟机的方法区实现方式)
    }
}
