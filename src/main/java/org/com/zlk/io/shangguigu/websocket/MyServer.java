package org.com.zlk.io.shangguigu.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author 会游泳的蚂蚁
 * @Description: Netty通过WebSocket 编程实现服务器和客户端长连接
 * @Date 2021/2/18 16:38
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
                    //基于http协议，使用http的编码和解码器
                    pipeline.addLast(new HttpServerCodec());
                    //以块方式写，添加 ChunkedWriteHandler处理器
                    pipeline.addLast(new ChunkedWriteHandler());

                    // http数据在传输过程中是分段, 这就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                    // HttpObjectAggregator可以将多个段聚合。
                    pipeline.addLast(new HttpObjectAggregator(8192));

                    // websocket的数据是以帧(frame) 形式传递;
                    // WebSocketFrame有六个子类
                    // 请求 ws://localhost:7000/hello2
                    // WebSocketServerProtocolHandler核心功能是将http协议升级为ws协议(状态码101),保持长连接
                    pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                    //自定义handler，处理业务逻辑
                    pipeline.addLast(new MyTextWebSocketFrameHandler());
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
