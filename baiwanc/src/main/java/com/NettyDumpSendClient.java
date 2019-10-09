package com;

//import com.crazymakercircle.netty.NettyDemoConfig;
//import com.crazymakercircle.util.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;
import java.util.logging.Logger;

/**
 * create by 尼恩 @ 疯狂创客圈
 **/
public class NettyDumpSendClient {

    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyDumpSendClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
    }

    public void runClient() {
        //创建reactor 线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //1 设置reactor 线程组
            b.group(workerLoopGroup);
            //2 设置nio类型的channel
            b.channel(NioSocketChannel.class);
            //3 设置监听端口
            b.remoteAddress(serverIp, serverPort);
            //4 设置通道的参数
            b.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            //5 装配子通道流水线
            b.handler(new ChannelInitializer<SocketChannel>() {
                //有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline管理子通道channel中的Handler
                    // 向子channel流水线添加一个handler处理器
//                    ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            });

            ChannelFuture f = b.connect();
            /*f.addListener((ChannelFuture futureListener) ->
            {
                if (futureListener.isSuccess()) {
                    Logger.info("EchoClient客户端连接成功!");

                } else {
                    Logger.info("EchoClient客户端连接失败!");
                }

            });*/

            // 阻塞,直到连接完成
            f.sync();
            Channel channel = f.channel();

            //6发送大量的文字
            byte[] bytes = null;
            for (int dou = 0; dou < 1000000; dou++) {
//            double dou = 0l;
//            while (true) {
                //发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                bytes = ("1疯狂创客圈：高性能学习社群!"+dou+++"\n").getBytes(Charset.forName("utf-8"));
                Thread.sleep(1);
                buffer.writeBytes(bytes);
//                buffer.writeInt(i);
                channel.writeAndFlush(buffer);
                System.out.println(channel.id()+"---client-id");
//                channel.

            }
            System.out.println("sleep");
            Thread.sleep(1000*60);


            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
//            ChannelFuture closeFuture =channel.closeFuture();
//            closeFuture.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup，
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
//        int port = NettyDemoConfig.SOCKET_SERVER_PORT;
//        String ip = NettyDemoConfig.SOCKET_SERVER_IP;
        new NettyDumpSendClient("192.168.1.108", 9998).runClient();
    }
}