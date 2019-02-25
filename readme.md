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


