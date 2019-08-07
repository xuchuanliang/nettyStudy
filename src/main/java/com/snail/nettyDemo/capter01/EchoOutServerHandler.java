package com.snail.nettyDemo.capter01;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author xuchuanliangbt
 * @title: EchoOutServerHandler
 * @projectName netty-study
 * @description:
 * @date 2019/8/713:17
 * @Version
 */
@ChannelHandler.Sharable
public class EchoOutServerHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println(msg);
    }
}
