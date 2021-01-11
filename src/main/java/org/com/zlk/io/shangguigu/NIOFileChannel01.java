package org.com.zlk.io.shangguigu;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 写数据到本地文件
 * @Date 2021/1/11 15:12
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello,zlkzuocheng";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zuocheng/Downloads/nettyDemo/file01.txt");
        //通过fileOutputStream获取对应的FileChannel,这个fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());
        //对byteBuffer进行flip反转
        byteBuffer.flip();
        //将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }
}
