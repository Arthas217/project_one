package org.com.zlk.io.shangguigu.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 自定义Handler，接收和发送信息
 * SimpleChannelInboundHandler是ChannelInboundHandlerAdapter子类
 * HttpObject客户端和服务器端相互通讯的数据被封装成的
 * @Date 2021/1/15 16:19
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // channelRead0 引起的问题
        // java.lang.NoSuchMethodError: io.netty.util.internal.AppendableCharSequence.setLength(I)V
        cause.printStackTrace();
        ctx.close();
    }

    // 读取客户端数据，debug时，方法进不去，原因是 netty的jar版本对应不上去
    // 你不用关心释放资源，这个方法重写了ChannelInboundHandlerAdapter类中的channelRead，主要客户端和服务端数据类型一致
    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断msg是不是httpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("pipeline hashcode" + ctx.pipeline().hashCode() + " NettyHttpServerHandler hash=" + this.hashCode());
            System.out.println("msg类型=" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest) msg;
            //获取uri,过滤指定的资源
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了 favicon.ico, 不做响应");
                return;
            }
            //回复信息给浏览器[http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);
            //netty支持http协议，有相应的接口，构造一个http的相应，即httpResponse
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //将构建好response返回
            ctx.writeAndFlush(response);
        }
    }
}
