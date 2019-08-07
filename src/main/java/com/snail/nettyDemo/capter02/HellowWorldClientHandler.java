package com.snail.nettyDemo.capter02;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuchuanliangbt
 * @title: HellowWorldClientHandler
 * @projectName netty-study
 * @description:
 * @date 2019/8/713:36
 * @Version
 */
public class HellowWorldClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HellowWorldClientHandler active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HellowWorldClientHandler read message:"+msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HellowWorldClientHandler exceptionCaught:"+cause.toString());
    }
}
