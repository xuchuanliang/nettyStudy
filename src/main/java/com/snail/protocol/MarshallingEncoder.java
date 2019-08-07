package com.snail.protocol;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;

/**
 * Netty消息编码工具类
 */
public class MarshallingEncoder {
    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];
    Marshaller marshaller;
    public MarshallingEncoder(){
    }
}
