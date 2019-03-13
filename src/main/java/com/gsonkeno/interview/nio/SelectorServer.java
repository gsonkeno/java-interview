package com.gsonkeno.interview.nio;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorServer {
    private static int PORT = 1234;

    public static void main(String[] args) throws Exception {
        // 先确定端口号
        int port = PORT;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        // 打开一个ServerSocketChannel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 获取ServerSocketChannel绑定的Socket
        ServerSocket ss = ssc.socket();
        // 设置ServerSocket监听的端口
        ss.bind(new InetSocketAddress(port));
        // 设置ServerSocketChannel为非阻塞模式
        ssc.configureBlocking(false);
        // 打开一个选择器
        Selector selector = Selector.open();
        // 将ServerSocketChannel注册到选择器上去并监听accept事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            // 这里会发生阻塞，等待就绪的通道
            int n = selector.select();
            // 没有就绪的通道则什么也不做
            if (n == 0) {
                continue;
            }
            // 获取SelectionKeys上已经就绪的通道的集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 遍历每一个Key
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                // 通道上是否有可接受的连接
                if (sk.isAcceptable()) {
                    System.out.println("通道上有可接受的连接，时间为" + System.currentTimeMillis());
                    ServerSocketChannel ssc1 = (ServerSocketChannel) sk.channel();
                    SocketChannel sc = ssc1.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }
                // 通道上是否有数据可读
                else if (sk.isReadable()) {
                    readDataFromSocket(sk);
                }
                iterator.remove();
            }
        }
    }

    private static ByteBuffer bb = ByteBuffer.allocate(1024);

    // 从通道中读取数据
    protected static void readDataFromSocket(SelectionKey sk) throws Exception {
        SocketChannel sc = (SocketChannel) sk.channel();
        bb.clear();
        while (sc.read(bb) > 0) {
            bb.flip();
            while (bb.hasRemaining()) {
                System.out.print((char) bb.get());
            }
            System.out.println();
            bb.clear();
        }
    }
}

