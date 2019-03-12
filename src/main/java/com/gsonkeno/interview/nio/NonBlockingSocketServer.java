package com.gsonkeno.interview.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
/**
 * https://www.cnblogs.com/xrq730/p/5176145.html
 * @author gaosong
 * @since 2019-03-12
 */
public class NonBlockingSocketServer {
    //因此当客户端Socket没有到来的时候选择的处理办法是每隔1秒钟轮询一次。

    public static void main(String[] args) throws Exception
    {
        int port = 1234;
        if (args != null && args.length > 0)
        {
            port = Integer.parseInt(args[0]);
        }
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));
        System.out.println("开始等待客户端的数据！时间为" + System.currentTimeMillis());
        while (true)
        {
            SocketChannel sc = ssc.accept();
            if (sc == null)
            {
                // 如果当前没有数据，等待1秒钟再次轮询是否有数据，在学习了Selector之后此处可以使用Selector
                Thread.sleep(1000);
                //System.out.println("睡眠1s");
            }
            else
            {
                System.out.println("客户端已有数据到来，客户端ip为:" + sc.socket().getRemoteSocketAddress()
                        + ", 时间为" + System.currentTimeMillis()) ;
                ByteBuffer bb = ByteBuffer.allocate(100);
                sc.read(bb);
                bb.flip();
                while (bb.hasRemaining())
                {
                    System.out.print((char)bb.get());
                }
                sc.close();
                System.exit(0);
            }
        }
    }
}
