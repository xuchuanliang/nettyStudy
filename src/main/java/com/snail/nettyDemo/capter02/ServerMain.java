package com.snail.nettyDemo.capter02;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xuchuanliangbt
 * @title: ServerMain
 * @projectName netty-study
 * @description:
 * @date 2019/8/713:26
 * @Version
 */
public class ServerMain {
    public static void main(String[] args){
        ServerBootstrap b = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        b.group(boss,work).channel(NioServerSocketChannel.class).localAddress(8888)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder",new StringDecoder());
                        ch.pipeline().addLast("encoder",new StringEncoder());
                        ch.pipeline().addLast(new HelloWorldServerHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = b.bind(8888).sync();
            System.out.println("server start listen at 8888");
            channelFuture.channel().closeFuture().sync();
            System.out.println("server stop");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
