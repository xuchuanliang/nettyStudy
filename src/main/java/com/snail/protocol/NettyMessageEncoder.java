package com.snail.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * Netty消息编码类
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    public NettyMessageEncoder(){
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
    }
}
