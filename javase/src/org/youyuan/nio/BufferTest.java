package org.youyuan.nio;

import java.nio.IntBuffer;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/9/20 16:56
 */
public class BufferTest {
    public static void main(String[] args) {
        //创建一个Int型的buffer，大小为5。相当于创建了一个大小为5的int数组
        IntBuffer buffer = IntBuffer.allocate(5);
        //往buffer中添加数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i);
        }
        //buffer读写切换，之前为写数据，调用flip后切换为读
        buffer.flip();

        //读取数据
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
