package com.gsonkeno.interview.jvm.understandJvm.chapter2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * jdk1.6 -XX:PermSize=10m -XX:MaxPermSize=10m,运行报错 Caused by: java.lang.OutOfMemoryError: PermGen space
 * 但是在jdk8中，应该这样设置 -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=7M
 * 报错 Caused by: java.lang.OutOfMemoryError: Metaspace
 * @author gaosong
 * @since 2019-03-24
 *
 * 借助cglib使方法区出现内存溢出异常
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj,args);
                }
            });

            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
