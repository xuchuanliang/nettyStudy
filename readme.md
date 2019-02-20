# 第一章 Netty-异步和事件驱动
- Netty的核心组件
>Channel:Channel是Java NIO的一个基本构造，它代表一个到实体的开放连接，如读操作和写操作。目前可以把Channel看做是传入或者传出数据的载体。因此
它可以被打开或者关闭，连接或者断开连接。
>回调：Netty内部使用回调来处理事件；当一个回调被触发时，相关的事件可以被一个InterfaceChannelHandler的实现处理。
>Future：提供了另一种在操作完成时通知应用程序的方式。
>事件和ChannelHandler：
2019年2月18日 12:30:00 40/272
2019年2月19日 08:26:43 46/272
# 第三章 Netty的组件和设计
Channel---Socket
EventLoop---控制流、多线程处理、并发
ChannelFuture---异步通知
### EventLoop接口
>EventLoop定义了netty的核心抽象，用于处理连接的生命周期中所发生的事件。图1在高层次上说明了Channel、EventLoop、Thread以及EventLoopGroup之间的关系。
这些关系是：1.一个EventLoopGroup包含一个或多个EventLoop；2.一个EventLoop在它的生命周期内只和一个Thread绑定；3.所有由EventLoop处理的I/O
事件都将在他专有的Thread上被处理；4.一个Channel在它的生命周期内只注册一个EventLoop；5.一个EventLoop可能会被分配给一个或多个Channel；
注意：在这种设计中，一个给定的Channel的I/O操作都是由相同的Thread执行的，实际上消除了对于同步的需要。