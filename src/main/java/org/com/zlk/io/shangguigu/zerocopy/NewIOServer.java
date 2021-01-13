package org.com.zlk.io.shangguigu.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 零拷贝 IO的服务端
 * @Date 2021/1/12 15:04
 */
public class NewIOServer {

    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(7002);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(address);
        //创建 buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readcount = 0;
            while (-1 != readcount) {
                try {
                    readcount = socketChannel.read(byteBuffer);
                } catch (Exception ex) {
                    // ex.printStackTrace();
                    break;
                }
                byteBuffer.rewind(); //倒带 position = 0 mark 作废
            }
        }
    }
}
