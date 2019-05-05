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
- **ChannelHandlerContext代表了ChannelHandler和ChannelPipeline之间的关联，每当有ChannelHandler添加至ChannelPipeline中时，都会创建ChannelHandlerContext**
- 当使用ChannelHandlerContext的API的时候，牢记以下两点：1.ChannelHandlerContext和ChannelHandler之间的关系（绑定）是永远不会改变的，所以缓存对它的引用是安全的；2.相对于
其他类的同名方法，ChannelHandlerContext的方法将产生更短的事件流，应该尽可能的利用这个特性来获得最大性能。
>**ChannelHandlerContext有很多方法，其中一些方法也存在于Channel和ChannelPipeline本身上，但是有一点不同，如果调用Channel或者ChannelPipeline上的这些方法，它们将沿着整个ChannelPipeline进行传播。
而调用位于ChannelHandlerContext上的相同方法，则将从当前所关联的ChannelHandler开始，并且只会传播给位于该ChannelPipeline中的下一个能够处理该事件的ChannelHandler**
- Channel、ChannelPipeline、ChannelHandler以及ChannelHandlerContext之间的关系如图4
- 通过Channel或者ChannelPipeline进行事件传播如图5
- 通过ChannelHandlerContext触发的操作的事件流，如图6
>因为一个ChannelHandler可以从属于多个ChannelPipeline，所以它可以绑定到多个ChannelHandlerContext实例。对于这种用法指在多个ChannelPipeline中共享同一个ChannelHandler，对应的ChannelHandler必须要使用@Sharable
注解标注；否则试图将它添加到多个ChannelPipeline时会触发异常。显而易见，为了安全的被用于多个并发的Channel，这样的ChannelHandler必须是线程安全的。
## 6.4异常处理

# 第七章 EventLoop和线程模型
2019年3月25日 08:15:48  114/272
## 7.3任务调度
 2019年3月25日 12:27:47 119/272
 
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
2019年4月22日 08:20:42 38/272
2019年4月22日 12:24:19 47/272
2019年4月23日 08:22:31 76/272
2019年4月23日 12:22:33 94/272
2019年4月24日 08:29:51 102/272

# 第七章 EventLoop和线程模型
- EventLoop类似于java中的ExecutorService，由一个不会改变的Thread驱动，同时任务可以直接提交给EventLoop实现，以立即执行或者调度执行。
## 7.4 EventLoop的实现细节
> Netty线程模型的卓越性能取决于对当前Thread身份的确定，也就是说，确定它是否是分配给当前Channel以及他的EventLoop的那个线程。如图7，
因此此处备注：**永远不要将一个长时间运行的任务放入到执行队列中，因为他将阻塞需要在同一个线程执行的任何任务，如果必须要进行阻塞调用或者执行长时间的任务，建议
使用一个专门的ExecutorService**   
> 异步传输实现只使用少量的EventLoop（以及和他们相关的Thread），而且在当前线程模型中，他们可能被多个Channel共享，这使得可以尽可能少量的Thread来支撑大量的Channel，
而不是每个Channel分配一个Thread，如图8。  
- 一旦一个Channel被分配给一个EventLoop，它将在他的生命周期中都使用这个EventLoop（以及那个相关联的Thread）。牢记这一点，
这样它就可以使你从担忧你的ChannelHandler实现中线程安全和同步问题中解脱出来。
>阻塞传输，如图9

# 第八章 引导
- 引导是将ChannelPipeline，ChannelHandler和EventLoop组织起来，成为一个实际可用的应用程序
- 引导一个应用程序是指对他进行配置，并使它运行起来的过程。
## 8.1 BootStrap类
- 类层次图参考图10
> 相对于具体的引导类分别看作用域服务器和客户端的引导来说，服务器致力于使用一个父的Channel来接受来自于客户端的连接，并创建子Channel以用于他们之间的通信；
而客户端最可能只需要一个单独的，没有父Channel的Channel来用于所有的网络交互。        
2019年4月25日 12:27:05 125/272

## 8.2 引导客户端和无链接协议
- 在引导过程中，在调用bind()或者connect之前，必须调用以下方法来设置所需的组件：
1.group();2.channel()或者channelFactory();3.handler(),如果不这样做，会导致异常，对handler的调用尤其重要，因为它需要配置好ChannelPipeline.

## 8.3 引导服务器
- ServerBootStrap引导过程如图11

2019年4月28日 11:53:55 137/272

--------------------------------------------------------------------------------------------------------------------------------------------------------------
# 第二部分 编解码器
# 第十章 编解码器框架
2019年4月29日 08:27:42 153/272

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
# 第三部分 网络协议
2019年4月29日 19:29:06 websocket