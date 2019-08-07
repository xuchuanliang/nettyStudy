package com.snail.nettyDemo.capter01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author xuchuanliangbt
 * @title: EchoServer
 * @projectName netty-study
 * @description:
 * @date 2019/8/711:06
 * @Version
 */
public class EchoServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        bootstrap
                .group(eventExecutors)
                .localAddress(new InetSocketAddress(8888))
                .channel(NioServerSocketChannel.class);
        EchoServerHandler serverHandler = new EchoServerHandler();
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(serverHandler);
            }
        });
        ChannelFuture sync = bootstrap.bind().sync();
        sync.channel().closeFuture().sync();
    }
}
