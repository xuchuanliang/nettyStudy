package com.snail.nettystudy.capter02.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 引导服务器
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port){
        this.port = port;
    }

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        if(args.length!=1){

        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }
    public void start() throws InterruptedException {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup eventExecutors = new NioEventLoopGroup();//创建EventLoopGroup
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();//创建ServerBootstrap
            serverBootstrap.group(eventExecutors)
                    .channel(NioServerSocketChannel.class)//指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port))//使用指定的端口设置套接字地址
                    .childHandler(new ChannelInitializer<SocketChannel>() {//添加一个EchoServerHandler到子Channel的ChannelPipeline
                        @Override
                        protected void initChannel(SocketChannel ch) throws InterruptedException {
                            ch.pipeline().addLast(echoServerHandler);//EchoServerHandler被标注为@Shareable，所以我们可以总是使用同样的实例
                        }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();//异步的绑定服务器，调用sync()方法阻塞等待直到绑定完成
            future.channel().closeFuture().sync();//获取Channel的CloseFuture，并且阻塞当前线程直到它完成
        }finally {
            eventExecutors.shutdownGracefully().sync();//关闭EventLoopGroup，释放所有资源
        }

    }
}
