package com.gsonkeno.proxyjdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * @author gaosong
 * @since 2019-06-18
 */
public class ProxyTicketCenter implements InvocationHandler {
    RealTicketCenter realTicketCenter;

    public ProxyTicketCenter(RealTicketCenter realTicketCenter) {
        this.realTicketCenter = realTicketCenter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("before");
        Object object = null;
        object=method.invoke(realTicketCenter,args);
        System.out.println("after");
        return object;
    }
}
