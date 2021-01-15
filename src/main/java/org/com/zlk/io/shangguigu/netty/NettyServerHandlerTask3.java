package org.com.zlk.io.shangguigu.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author 会游泳的蚂蚁
 * @Description: 非当前Reactor线程调用Channel的各种方法
 * @Date 2021/1/15 10:29
 */
public class NettyServerHandlerTask3 extends ChannelInboundHandlerAdapter {

    Set<SocketChannel> set;

    NettyServerHandlerTask3(Set<SocketChannel> ch) {
        this.set = ch;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送消息是:" + buf.toString(CharsetUtil.UTF_8));
        int size = set.size();
        Iterator<SocketChannel> iterator = set.iterator();
        System.out.println("set集合大小为" + size);
        if (iterator.hasNext()) {
            iterator.next().eventLoop().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1 * 1000);// 模拟耗时操作
                        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 2", CharsetUtil.UTF_8));
                    } catch (InterruptedException e) {
                        System.out.println("发生异常" + e.getMessage());
                    }
                }
            });
        }
        System.out.println("go on======");


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵 1", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
