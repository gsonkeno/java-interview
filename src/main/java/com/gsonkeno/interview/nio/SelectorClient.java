package com.gsonkeno.interview.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SelectorClient
{
    private static final String STR = "Hello World!";
    private static final String REMOTE_IP = "127.0.0.1";
    private static final int THREAD_COUNT = 5;

    private static class NonBlockingSocketThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                int port = 1234;
                SocketChannel sc = SocketChannel.open();
                sc.configureBlocking(false);
                sc.connect(new InetSocketAddress(REMOTE_IP, port));
                while (!sc.finishConnect())
                {
                    System.out.println("同" + REMOTE_IP + "的连接正在建立，请稍等！");
                    Thread.sleep(10);
                }
                String writeStr = STR + this.getName();
                System.out.println("连接已建立，待写入内容至指定ip+端口！时间为" + System.currentTimeMillis() + "，内容为" + writeStr);
                ByteBuffer bb = ByteBuffer.allocate(writeStr.length());
                bb.put(writeStr.getBytes());
                bb.flip(); // 写缓冲区的数据之前一定要先反转(flip)
                sc.write(bb);
                bb.clear();
                sc.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        NonBlockingSocketThread[] nbsts = new NonBlockingSocketThread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            nbsts[i] = new NonBlockingSocketThread();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            nbsts[i].start();
        }
        // 一定要join保证线程代码先于sc.close()运行，否则会有AsynchronousCloseException
        for (int i = 0; i < THREAD_COUNT; i++) {
            nbsts[i].join();
        }
    }
}
