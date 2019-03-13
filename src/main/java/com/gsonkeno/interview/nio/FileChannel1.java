package com.gsonkeno.interview.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://www.cnblogs.com/xrq730/p/5080503.html
 * @author gaosong
 * @since 2019-03-12
 */
public class FileChannel1 {

    /**
     *    使用文件通道读数据
     */
    public static void main(String[] args) throws IOException {
        File file = new File(FileChannel1.class.getResource("/").getPath() + File.separator  + "readchannel.txt");
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(35);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining())
        {
            System.out.print((char)bb.get());
        }
        bb.clear();
        fc.close();
    }

    @Test
    public void testWrite() throws IOException {
        File file = new File(FileChannel1.class.getResource("/").getPath() + File.separator
                + "writechannel.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fc = raf.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(10);
        String str = "abcdefghij";
        bb.put(str.getBytes());
        //写之前如果不flip会导致文件中无数据
        bb.flip();
        fc.write(bb);
        bb.clear();
        fc.close();
    }
}
