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

##ChannelHandler和ChannelPipeline
### ChannelHandler
>Netty的主要组件是ChannelHandler，它充当了所有处理入站和出站数据的应用程序逻辑的容器。
###ChannelPipeline接口
>ChannelPipeline提供了ChannelHandler链的容器，并定义了用于该链上传播入站和出站时间流的API。当Channel被创建时，他会被自动分配到他专属的CHannelPipeline。 如图2   
>从一个客户端应用程序的角度来看，如果事件的运动方向是从客户端到服务端，那么我们称这些事件时出站，反之称为入站。
## 引导
>Netty的引导类为应用程序的网络层配置提供了容器，这涉及将一个进程绑定到某个指定的端口，或者将一个进程连接到另一个运行在某个指定主机的指定端口上的进程，通常来说，
我们把前面的用例称作引导一个服务器，后面的用例称为引导一个客户端。因此，有两种类型的引导：一种用于客户端（简单称为Bootstrap），而另一种用于服务端（ServerBootStrap）

#第四章 传输
>每个Channel都将会分配一个ChannelPipeline和ChannelConfig。ChannelConfig包含了该Channel的所有配置设置，并且支持热更新。由于特定的传输可能有独特的设置，所有他可能会实现一个
ChannelConfig的子类型。
ChannelPipeline持有应用所有出站和入站数据以及事件的ChannelHandler实例，这些ChannelHandler实现了应用程序用于处理状态变化以及数据处理的逻辑。
ChannelHandler的典型用于包括：1.将数据从一种格式转换成另一种合适；2.提供异常的通知；3.提供Channel变为活动或非活动的通知；4.提供当Channel注册到EventLoop或者从EventLoop
注销时的通知；5.提供有关用户自定义事件的通知

## 4.3 内置的传输
### 4.3.1 NIO--非阻塞I/O
>NIO提供了一个所有I/O操作的全异步的实现，它利用了自NIO子系统被引入JDK1.4时便可用的基于选择器的API。选择器背后的基本概念是充当一个注册表，在那里你将可以请求在Channel的状态
发生变化时得到通知，可能的状态变化有：1.新的Channel已被接受并且就绪；2.Channel连接已经完成；3.Channel有已经就绪可供读取的数据；4.Channel可用于写数据。
Netty选择并处理状态的流程如图3
- 零拷贝：是一种目前只有在使用NIO和Eoll传输时才可使用的特性。它可使你快速高效的将数据从文件系统移动到网络接口，而不需要将其从内核空间复制到用户空间。
### 4.3.2 Epoll-用于Linux的本地非阻塞传输
## 4.4 传输的用例

# 第五章 ByteBuf
2019年2月26日 08:21:21 76/272
- ByteBuf的优点：1.它可以被用户自定义的缓冲区类型扩展；2.通过内置的复合缓冲区类型实现了透明的零拷贝；3.容量可以按需增长（类似于JDK的StringBuilder）；4.在读和写这两种模式之间切换
不需要调用ByteBuf的flip()方法；5.读和写使用了不同的索引；6.支持方法的链式调用；7.支持引用计数；8.支持池化
## 5.2 ByteBuf类--Netty
- 堆缓冲区
- 直接缓冲区
- 复合缓冲区
2019年3月5日 12:20:54  81/272
## 字节级操作
- 标记可被丢弃字节的分段包含了已经被读过的字节。通过调用discardReadBytes()方法，可以丢弃他们并且回收内存，但是频繁调用discardReadBytes()方法极有可能导致内存复制，因为可读字节必须被移动到缓冲区的开始
位置，建议只有在真正需要的时候再这么做。
- 通过调用clear方法来将readIndex和writerIndex都设置为0，注意这并不会清除内存中的内容，调用clear方法比调用discardReadBytes轻量得多，因为他只是将索引重置而不会复制任何内存。
- slice()方法和copy()方法，slice()方法是保持同一个引用，如果源或者新的数据改动，则所有的均改动，copy()是独立的拷贝，只要有可能，使用slice()方法来避免复制内存的开销。
2019年3月19日 20:24:31 94/272

# 第六章 ChannelHandler和ChannelPipeline
## ChannelHandler
> Channel的四个状态：ChannelUnregistered【Channel已经被创建，但还未注册到EventLoop】，
ChannelRegistered【Channel已经被注册到EventLoop】，
ChannelActive【Channel处于活跃状态，现在可以接收和发送数据】，
ChannelInactive【Channel没有连接到远程节点】   
>ChannelHandler的两个重要子接口：ChannelInboundHandler【处理入站数据以及各种状态变化】和ChannelOutboundHandler【处理出栈数据并且允许拦截所有的操作】

2019年3月20日 12:31:08 100/272
## 6.2 ChannelPipeline接口
## 6.3 ChannelHandlerContext接口
- ChannelHandlerContext代表了ChannelHandler和ChannelPipeline之间的关联，每当有ChannelHandler添加至ChannelPipeline中时，都会创建ChannelHandlerContext
- 当使用ChannelHandlerContext的API的时候，牢记以下两点：1.ChannelHandlerContext和ChannelHandler之间的关系（绑定）是永远不会改变的，所以缓存对它的引用是安全的；2.相对于
其他类的同名方法，ChannelHandlerContext的方法将产生更短的事件流，应该尽可能的利用这个特性来获得最大性能。
>**ChannelHandlerContext有很多方法，其中一些方法也存在于Channel和ChannelPipeline本身上，但是有一点不同，如果调用Channel或者ChannelPipeline上的这些方法，它们将沿着整个ChannelPipeline进行传播。
而调用位于ChannelHandlerContext上的相同方法，则将从当前所关联的ChannelHandler开始，并且只会传播给位于该ChannelPipeline中的下一个能够处理该事件的ChannelHandler**
- Channel、ChannelPipeline、ChannelHandler以及ChannelHandlerContext之间的关系如图4
- 通过Channel或者ChannelPipeline进行事件传播如图5

2019年3月22日 12:28:39 107/272