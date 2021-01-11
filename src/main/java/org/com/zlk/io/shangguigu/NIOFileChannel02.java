package org.com.zlk.io.shangguigu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 将file01.txt 中的数据读入到程序，并显示在控制台屏幕
 * @Date 2021/1/11 15:32
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {
        //创建文件的输入流
        File file = new File("/Users/zuocheng/Downloads/nettyDemo/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过 fileInputStream 获取对应的 FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通道的数据读入到Buffer
        fileChannel.read(byteBuffer);
        //将byteBuffer的字节数据转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

}
