package org.com.zlk.io.shangguigu;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 理解NIO非阻塞网络编程机制 服务端
 * @Date 2021/1/11 19:43
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        //创建 ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定一个端口 6666, 在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //一个 Selector 对象
        Selector selector = Selector.open();
        //把 serverSocketChannel 注册到 selector 关心事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端注册后selectionkey数量"+ selector.keys().size());
        //循环等待客户端连接
        while (true) {
            //这里我们等待1秒，如果没有事件发生,返回
            if (selector.select(1000) == 0) { //没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            //如果返回的>0, 就获取到相关的selectionKey集合,表示已经获取到关注的事件
            //返回关注事件的集合
            //通过selectionKeys反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件做相应处理
                if (key.isAcceptable()) { //如果OP_ACCEPT, 有新的客户端连接
                    //该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println(" 客 户 端 连 接 成 功 生 成 了 一 个 socketChannel " + socketChannel.hashCode());
                    //将SocketChannel 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector, 关注事件为 OP_READ，同时给socketChannel关联一个Buffer（服务端缓存）
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后，注册后selectionkey数量"+ selector.keys().size());
                    System.out.println("客户端连接后，注册通道发生事件selectionkey数量"+ selector.selectedKeys().size());
                }
                if (key.isReadable()) { //发生OP_READ
                    //通过key反向获取到对应channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer 即上面的ByteBuffer.allocate(1024)
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }
                //手动从集合中移动当前的selectionKey, 防止重复操作
                keyIterator.remove();
            }
        }
    }
}
