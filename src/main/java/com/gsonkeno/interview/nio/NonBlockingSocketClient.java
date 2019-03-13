package com.gsonkeno.interview.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * https://www.cnblogs.com/xrq730/p/5176145.html
 * @author gaosong
 * @since 2019-03-12
 */
public class NonBlockingSocketClient {
    private static final String STR = "Hello World!";
    private static final String REMOTE_IP= "127.0.0.1";

    public static void main(String[] args) throws Exception
    {
        int port = 1234;
        if (args != null && args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        }
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(REMOTE_IP, port));
        while (!sc.finishConnect())
        {
            System.out.println("同" + REMOTE_IP+ "的连接正在建立，请稍等！");
            Thread.sleep(10);
        }
        System.out.println("连接已建立，待写入内容至指定ip+端口！时间为" + System.currentTimeMillis());
        ByteBuffer bb = ByteBuffer.allocate(STR.length());
        bb.put(STR.getBytes());
        bb.flip(); // 写缓冲区的数据之前一定要先反转(flip)
        sc.write(bb);
        bb.clear();
        sc.close();
    }
}
