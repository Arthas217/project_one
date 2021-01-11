package org.com.zlk.io.shangguigu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 使用一个 Buffer 完成文件读取、写入。拷贝文本文件1.txt为2.text文件
 * @Date 2021/1/11 15:44
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        //循环读取
        while (true) {
            //清空buffer
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read =" + read);
            //表示读完
            if (read == -1) {
                break;
            }
            //将buffer中的数据写入到fileChannel02
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
