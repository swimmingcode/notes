package org.youyuan.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/9/20 17:03
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        //创建一个文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream("E://a.txt");
        //通过文件输出流得到一个FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个buffer并写入数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("swimcode".getBytes());
        //方转，让指针指向数组开头
        buffer.flip();

        //将Buffer中数据写入FileChannel中
        fileChannel.write(buffer);
        fileChannel.close();
        fileOutputStream.flush();
        fileOutputStream.close();

    }
}
