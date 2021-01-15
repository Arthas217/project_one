package org.com.zlk.io.shangguigu.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: Netty线程模型服务器端
 * @Date 2021/1/12 18:58
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建 BossGroup 和 WorkerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        //1. 创建两个线程组 bossGroup 和 workerGroup
        //2. bossGroup 只是处理连接请求 , 真正的和客户端业务处理，会交给 workerGroup 完成
        //3. 两个都是无限循环
        //4. bossGroup和workerGroup含有的子线程(NioEventLoop)的个数 默认实际cpu核数*2

        Set<SocketChannel> socketChannelSet = new HashSet<>();
        try {
            //创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            bootstrap.group(bossGroup, workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class) //作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() { //创建一个通道测试对象(匿名对象),给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyServerHandler());// 自定义Handler

                            // 有3种典型使用场景
//                            ch.pipeline().addLast(new NettyServerHandlerTask1());

//                            ch.pipeline().addLast(new NettyServerHandlerTask2());

                            // 可以使用一个集合管理SocketChannel，在推送消息时可以将业务加入到各个SocketChannel对应的NIOEventLoop的taskqueue或者是scheduledTaskQueue
//                            System.out.println("客户端SocketChannel hashcode= "+ch.hashCode());
//                            socketChannelSet.add(ch);
//                            ch.pipeline().addLast(new NettyServerHandlerTask3(socketChannelSet));
                        }
                    });// 给我们的 workerGroup 的 EventLoop 对应的管道设置处理器
            System.out.println(".....服务器 is ready...");
            //绑定一个端口并且同步, 生成了一个 ChannelFuture 对象
            //启动服务器(并绑定端口)
            ChannelFuture channelFuture = bootstrap.bind(6668).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
