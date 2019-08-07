package com.snail.nettyDemo.capter02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuchuanliangbt
 * @title: HelloWorldServerHandler
 * @projectName netty-study
 * @description:
 * @date 2019/8/713:23
 * @Version
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloWorldServerHandler active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HelloWorldServerHandler read message:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HelloWorldServerHandler exceptionCaught:"+cause.toString());
    }
}
