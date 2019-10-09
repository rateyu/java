package com; /**
 * create by 尼恩 @ 疯狂创客圈
 **/

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class StringProcessHandler extends ChannelInboundHandlerAdapter {
//    public static final StringProcessHandler INSTANCE = new StringProcessHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String) msg;
        System.out.println("打印: " + s);
//        ctx.writeAndFlush()

//        ctx.writeAndFlush(msg+"--fanhui");
    }
}