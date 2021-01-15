package org.com.zlk.io.shangguigu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 队列中Task 用户自定义定时任务
 * @Date 2021/1/15 10:29
 */
public class NettyServerHandlerTask2 extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件(我们可以读取客户端发送的消息)
     * ChannelHandlerContext ctx:上下文对象, 含有管道pipeline , 通道channel, 地址
     * msg: 就是客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //比如这里我们有一个非常耗时长的业务-> 异步执行 -> 提交该channel对应的NIOEventLoop的taskQueue中

        //将 msg 转成一个Netty提供的ByteBuf
        Channel channel = ctx.channel();
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + channel.remoteAddress());


        //解决方案 2 : 用户自定义定时任务 -》 该任务是提交到 scheduledTaskQueue 中
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 4", CharsetUtil.UTF_8));
                    System.out.println(" channel code=" + ctx.channel().hashCode() + " time=" + System.currentTimeMillis());
                } catch (Exception ex) {
                    System.out.println("发生异常" + ex.getMessage());
                }
            }
        }, 5, TimeUnit.SECONDS);
        System.out.println("go on  ======");


    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存，并刷新。一般讲，我们对这个发送的数据进行编码
        System.out.println("time===" + System.currentTimeMillis());
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 1", CharsetUtil.UTF_8));
    }

    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
