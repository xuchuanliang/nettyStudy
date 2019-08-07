package com.snail.protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息头Header类定义
 * crcCode：整型int|32|Netty消息校验码，由三部分组成：1.0xABEF:固定值，表名该消息是Netty协议消息，2字节；
 * 2.主版本号：1-255，一个字节；次版本号：1-255，一个字节
 * length：整型int|32|消息长度，整个消息，包括消息头和消息体
 * sessionID：长整型long|64|集群节点内全局唯一，由会话ID生成器生成
 * type：Byte|8|0：业务请求数据；1：业务响应数据；2：业务ONE WAY消息（既是请求又是响应消息）；3：握手请求消息；4：握手应答消息；
 * 5：心跳请求消息；6：心跳应答消息
 * priority：Byte|8|消息优先级：0~255
 * attachment：Map<String,Object>|变长|可选字段，用于扩展消息头
 */
public class Header implements Serializable {
    private int crcCode = 0xabef0101;
    /**
     * 消息长度
     */
    private int length;
    /**
     * 会话ID
     */
    private long sessionID;
    /**
     * 消息类型
     */
    private byte type;
    /**
     * 消息优先级
     */
    private byte priority;
    /**
     * 附件
     */
    private Map<String,Object> attachment = new HashMap<>();

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionID=" + sessionID +
                ", type=" + type +
                ", priority=" + priority +
                ", attachment=" + attachment +
                '}';
    }
}
