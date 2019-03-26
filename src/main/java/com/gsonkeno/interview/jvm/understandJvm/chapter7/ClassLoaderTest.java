package com.gsonkeno.interview.jvm.understandJvm.chapter7;

import java.io.IOException;
import java.io.InputStream;
/**
 * 虚拟机中存在两个ClassLoaderTest类，
 * 一个是系统应用程序类加载器加载的，
 * 一个是自定义的类加载器加载的
 * @author gaosong
 * @since 2019/3/26
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null){
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                } catch (IOException e) {
                   throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.gsonkeno.interview.jvm.understandJvm.chapter7.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest); //false
    }
}
