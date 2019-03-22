package com.gsonkeno.interview.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * https://blog.csdn.net/pange1991/article/details/81941597
 * 其实，移除永久代的工作从JDK1.7就开始了。
 * JDK1.7中，存储在永久代的部分数据就已经转移到了Java Heap或者是 Native Heap。
 * 但永久代仍存在于JDK1.7中，并没完全移除，譬如符号引用(Symbols)转移到了native heap；
 * 字面量(interned strings)转移到了java heap；
 * 类的静态变量(class statics)转移到了java heap。
 * 我们可以通过一段程序来比较 JDK 1.6 与 JDK 1.7及 JDK 1.8 的区别，以字符串常量为例
 * @author gaosong
 * @since 2019-03-22
 */
public class StringOomMock {

    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
