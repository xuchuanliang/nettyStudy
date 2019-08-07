package com.snail.nettyDemo.capter01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xuchuanliangbt
 * @title: EchoClient
 * @projectName netty-study
 * @description:
 * @date 2019/8/711:23
 * @Version
 */
public class EchoClient {
    public static void main(String[] args) throws InterruptedException {
        EchoClientHandler echoClientHandler = new EchoClientHandler();
        Bootstrap b = new Bootstrap().group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress("127.0.0.1",8888))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(echoClientHandler);
                    }
                });
        ChannelFuture channelFuture = b.connect().sync();
        channelFuture.channel().closeFuture().sync();
    }
}
