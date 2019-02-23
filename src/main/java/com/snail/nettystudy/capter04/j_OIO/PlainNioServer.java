package com.snail.nettystudy.capter04.j_OIO;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用java nio编写
 */
public class PlainNioServer {
    public void server(int port){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            //将服务器绑定到选定端口
            serverSocket.bind(inetSocketAddress);
            //打开Selector来绑定Channel
            Selector selector = Selector.open();
            //将ServerSocket注册到Selector以接受连接
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
            final ByteBuffer byteBuffer = ByteBuffer.wrap("Hi,/r/n".getBytes(CharsetUtil.UTF_8));
            for(;;){
                //等待需要处理的新事件，阻塞将一直持续到下一个传入事件
                selector.select();
                //获取所有接收事件Selection实例
                Set<SelectionKey> keys = selector.keys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    //检查事件是否是一个新的已经就绪可以被接受的连接
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE | SelectionKey.OP_READ,byteBuffer.duplicate());
                        System.out.println("accepted connection from "+client);
                        //检查套接字是否已经准备好数据
                        if(key.isWritable()){
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (buffer.hasRemaining()){
                                if(client.write(buffer) == 0){
                                    break;
                                }
                            }
                            client.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
