package com.snail.nettystudy.capter04.n_OIO;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.*;

/**
 * netty阻塞版本
 * @author xuchuanliangbt
 */
public class NettyOioServer {
    public static ExecutorService myPool = new ThreadPoolExecutor(5,10,1000*60*60, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1000));
    public void server(int port){
        final ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hi,\r\n",CharsetUtil.UTF_8));
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            //创建ServerBootStrap
            ServerBootstrap b = new ServerBootstrap();
            //使用OioEventLoopGroup以允许阻塞模式（旧的IO）
            b.group(group)
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //指定ChannelInitializer，对于每个已接收的连接都调用它
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            //添加一个ChannelInboundHandlerAdapter以拦截和处理事件
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                //将消息写到客户端，并添加ChannelFutureListener以便消息一被写完就关闭连接
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate())
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            //绑定服务器以接受连接
            ChannelFuture f = b.bind().sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {

        }finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
