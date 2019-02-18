# 第一章 Netty-异步和事件驱动
- Netty的核心组件
>Channel:Channel是Java NIO的一个基本构造，它代表一个到实体的开放连接，如读操作和写操作。目前可以把Channel看做是传入或者传出数据的载体。因此
它可以被打开或者关闭，连接或者断开连接。
>回调：Netty内部使用回调来处理事件；当一个回调被触发时，相关的事件可以被一个InterfaceChannelHandler的实现处理。
>Future提供了另一种在操作完成时通知应用程序的方式。