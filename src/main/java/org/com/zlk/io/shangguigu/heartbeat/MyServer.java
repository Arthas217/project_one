package org.com.zlk.io.shangguigu.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 心跳
 * @Date 2021/2/18 16:07
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    //加入一个 netty 提供 IdleStateHandler处理空闲状态的处理器
                    //参数（多长时间没有读,多长时间没有写，多长时间没有读写   就会发送一个心跳检测包 检测是否连接）
                    pipeline.addLast(new IdleStateHandler(13, 5, 10, TimeUnit.SECONDS));
                    // 当IdleStateEvent触发后，就会传递给管道的下一个handler去处理，[通过回调用(触发)下一个handler的userEventTriggered方法]
                    // userEventTriggered在该方法中去处理IdleStateEvent(读 空闲，写空闲，读写空闲)
                    // 对一个空闲检测进一步处理的 handler(自定义)
                    pipeline.addLast(new MyServerHandler());
                }
            });
            //启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(7001).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }
}
