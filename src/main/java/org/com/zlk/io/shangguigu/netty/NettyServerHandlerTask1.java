package org.com.zlk.io.shangguigu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 队列中Task 用户自定义普通任务（耗时长、异步处理，主要看channelRead函数）
 * @Date 2021/1/15 10:29
 */
public class NettyServerHandlerTask1 extends ChannelInboundHandlerAdapter {

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


        //解决方案 1 用户程序自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "-5s");
                    Thread.sleep(5 * 1000);// 模拟耗时操作
                    // 不使用异步，客户端和服务端都会阻塞5s后再执行下面的代码
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 2", CharsetUtil.UTF_8));
                    System.out.println("time=喵2  " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });
        // 目前该异步会使服务端不阻塞输出
        System.out.println(Thread.currentThread().getName() + "----");
        System.out.println("go on  channel code=" + ctx.channel().hashCode());
        System.out.println("time" + System.currentTimeMillis());

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "-10s");
                    Thread.sleep(10 * 1000);// 模拟耗时操作
                    // 不使用异步，客户端和服务端都会阻塞5s后再执行下面的代码
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 3", CharsetUtil.UTF_8));
                    System.out.println("time==喵3  " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    System.out.println("发生异常" + e.getMessage());
                }
            }
        });
        System.out.println("go on======");


    }

    //数据读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存，并刷新。一般讲，我们对这个发送的数据进行编码
        System.out.println("time-喵1  " + System.currentTimeMillis());
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 1", CharsetUtil.UTF_8));
    }

    //处理异常, 一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
