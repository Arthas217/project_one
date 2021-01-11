package org.com.zlk.io.shangguigu;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 拷贝文件 transferFrom 方法
 * @Date 2021/1/11 16:08
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/zuocheng/Downloads/nettyDemo/1.pdf");
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zuocheng/Downloads/nettyDemo/2.pdf");
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();
        destCh.transferFrom(sourceCh,0,sourceCh.size());
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
