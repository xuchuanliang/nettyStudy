package com.snail.nettystudy.capter04.j_NIO;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用java api编写阻塞io
 * @author xuchuanliangbt
 */
public class PlainOioServer {
    public void server(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            for(;;){
                final Socket socket = serverSocket.accept();
                System.out.println(String.format("accepted connection from:{0}",socket));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write("hi /r/n".getBytes(CharsetUtil.UTF_8));
                            outputStream.flush();
                            outputStream.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
