package com.snail.nettyHellowWorld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author xuchuanliangbt
 * @title: NettyServer
 * @projectName netty-study
 * @description:
 * @date 2019/8/717:23
 * @Version
 */
public class NettyServer {
    public static void main(String[] args){
        //netty服务端，创建boss线程和work线程
        //创建boss线程，用于服务端接受客户端请求，转发客户端请求至work线程
        EventLoopGroup boss = new NioEventLoopGroup(1);
        //创建work线程组，用于进行SocketChannel的数据读写
        EventLoopGroup work = new NioEventLoopGroup();
        //创建服务器端引导类
        ServerBootstrap b = new ServerBootstrap();
        //使用EventLoopGroup线程组
        b.group(boss,work)
                //设置为Nio模式，设置需要实例化的NioServerSocketChannel类型
                .channel(NioServerSocketChannel.class)
                //绑定本地服务器端口为8888
                .localAddress(new InetSocketAddress(8888))
                //使用netty提供的日志打印处理器
                .handler(new LoggingHandler(LogLevel.INFO))
                //创建基于TCP链接的channel
                //netty中的Channel连接到不同的地方执行I/O操作将channel分类以下四种：
                // FileChannel：文件通道，连接到文件以进行文件的I/O
                // SocketChannel：基于TCP可靠连接的通道，
                // DataGramChannel基于UDP的通道，
                // ServerSocketChannel：监听套接字的可选择通道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder(),new StringDecoder());
                    }
                });
    }
}
