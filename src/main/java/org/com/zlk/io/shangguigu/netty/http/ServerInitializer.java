package org.com.zlk.io.shangguigu.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 抽出服务端设置childHandler中逻辑
 * @Date 2021/1/15 16:21
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //向管道加入处理器
        ChannelPipeline pipeline = ch.pipeline();
        // codec 含义 [coder - decoder]
        // HttpServerCodec是netty提供的处理http的编-解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        // 增加一个自定义的handler
        pipeline.addLast(new HttpServerHandler());
        System.out.println("OK ……");
    }
}
