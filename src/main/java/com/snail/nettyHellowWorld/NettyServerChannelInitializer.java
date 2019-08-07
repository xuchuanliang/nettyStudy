package com.snail.nettyHellowWorld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author xuchuanliangbt
 * @title: NettyServerChannelInitializer
 * @projectName netty-study
 * @description:
 * @date 2019/8/717:41
 * @Version
 */
public class NettyServerChannelInitializer extends ChannelInitializer<ServerSocketChannel> {
    @Override
    protected void initChannel(ServerSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringEncoder(),new StringDecoder());
    }
}
class ServerSocketChannelHandler extends SimpleChannelInboundHandler<String>{


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }
}
