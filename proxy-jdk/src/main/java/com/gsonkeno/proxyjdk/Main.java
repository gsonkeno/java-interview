package com.gsonkeno.proxyjdk;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        // 保留生成的字节码文件,在根项目下的com/sun/proxy目录下，会有一个$Proxy0.class类文件
        // 该类继承了Proxy,且实现了用户的代理类接口
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        TicketCenter ticketCenter = (TicketCenter) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{TicketCenter.class},
                new ProxyTicketCenter(new RealTicketCenter()));
        ticketCenter.buyTicket(10);
    }
}
