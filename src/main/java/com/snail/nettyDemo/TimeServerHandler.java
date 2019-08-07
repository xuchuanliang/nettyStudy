package com.snail.nettyDemo;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author xuchuanliangbt
 * @title: TimeServerHandler
 * @projectName netty-study
 * @description:
 * @date 2019/8/710:47
 * @Version
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
