package org.com.zlk.io.shangguigu;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要拷贝一次
 * @Date 2021/1/11 16:25
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2: 0: 可以直接修改的起始位置
         * 参数3: 5: 将1.txt的多少个字节映射到内存 ,可以直接修改的范围就是 0-5 是映射到内存的大小(不是索引位置) ,
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        mappedByteBuffer.put(5, (byte) 'Y'); //IndexOutOfBoundsException
        randomAccessFile.close();
        System.out.println("修改成功~~");
    }
}
