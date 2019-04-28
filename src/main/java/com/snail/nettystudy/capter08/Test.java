package com.snail.nettystudy.capter08;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Test {
    public static void main(String[] args){

    }

    public void test1(){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new SimpleChannelInboundHandler<ByteBuf>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                        System.out.println("receive data");
                    }
                });
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("10.100.33.32", 80));
        connect.addListener((ChannelFuture future) ->{
                if(future.isSuccess()){
                    System.out.println("connect success");
                }else {
                    System.out.println("exception");
                    future.cause().printStackTrace();
                }
            }
        );
    }
}
