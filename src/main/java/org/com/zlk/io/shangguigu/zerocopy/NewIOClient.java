package org.com.zlk.io.shangguigu.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 零拷贝 IO的客户端
 * @Date 2021/1/12 15:05
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7002));
        String filename = "/Users/zuocheng/Downloads/dubbo-2.5.x.zip";
        //得到一个文件 channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();
        //在 linux 下一个 transferTo 方法就可以完成传输
        //在 windows 下 一次调用 transferTo只能发送 8m , 就需要分段传输文件,要注意传输时的位置
        // transferTo底层使用到零拷贝
        // 许多操作系统可以将字节直接从文件系统高速缓存传输到目标通道，而无需实际复制它们。
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
        //关闭
        fileChannel.close();
    }
}
