package com.snail.protocol;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xuchuanliangbt
 * @title: TimeClientHandler
 * @projectName netty-study
 * @description:
 * @date 2019/8/78:39
 * @Version
 */
public class TimeClientHandler implements Runnable{
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile  boolean stop;

    public TimeClientHandler(String host,int port) throws IOException {
        this.host = host;
        this.port = port;
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
    }


    @Override
    public void run() {
        try {
            //写数据
            if(socketChannel.connect(new InetSocketAddress(host,port))){
                byte[] req = "snail is so cool,so good,come on,not fail".getBytes();
                ByteBuffer write = ByteBuffer.allocate(req.length);
                write.put(req);
                write.flip();
                socketChannel.write(write);
                if(!write.hasRemaining()){
                    //无剩余，写出成功
                }
            }else{
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }

            while (!stop){
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    if(key.isValid()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        if(key.isConnectable()){
                            if(channel.finishConnect()){
                                channel.register(selector,SelectionKey.OP_READ);
                                byte[] req = "snail is so cool,so good,come on,not fail".getBytes();
                                ByteBuffer write = ByteBuffer.allocate(req.length);
                                write.put(req);
                                write.flip();
                                channel.write(write);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
