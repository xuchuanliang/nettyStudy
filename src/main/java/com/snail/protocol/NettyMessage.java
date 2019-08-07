package com.snail.protocol;

import java.io.Serializable;

/**
 * Netty协议栈使用到的数据结构
 */
public class NettyMessage implements Serializable {
    /**
     * 消息头
     */
    private Header header;
    /**
     * 消息体
     */
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
