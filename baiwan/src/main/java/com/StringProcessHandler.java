package com; /**
 * create by 尼恩 @ 疯狂创客圈
 **/

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicLong;

public class StringProcessHandler extends ChannelInboundHandlerAdapter {

    final Logger logger = LoggerFactory.getLogger(StringProcessHandler.class);
//    AtomicLong atomicLong = new AtomicLong(0);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String) msg;
//        logger.info("");
//        logger.info("打印: [{}]" , s);
//        logger.info(""+);
//        ctx.writeAndFlush()

        byte[] bytes = null;
        ByteBuf buffer = ctx.alloc().buffer();
        bytes = ("1疯狂创客圈：高性能学习社群!---fanhui\n").getBytes(Charset.forName("utf-8"));
//        Thread.sleep(1);
        buffer.writeBytes(bytes);
//        System.out.println(ctx.channel().id()+"--handle_id");
        ChannelFuture f = ctx.writeAndFlush(buffer);
        f.addListener((ChannelFuture futureListener) -> {
//                logger.info("写回后：{[]}",msg);
            logger.info("msg.refCnt:[{}]",msg);
        });
    }
}