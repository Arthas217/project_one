package org.com.zlk.io.shangguigu.groupchat.version2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/16 21:30
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    // 读取客户端发送的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
