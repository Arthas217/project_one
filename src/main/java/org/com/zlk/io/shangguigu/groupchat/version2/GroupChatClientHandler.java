package org.com.zlk.io.shangguigu.groupchat.version2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author 会游泳的蚂蚁
 * @Description:
 * @Date 2021/1/16 21:36
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    //接收服务端信息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }
}
