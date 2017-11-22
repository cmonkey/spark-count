package org.github.cmonkey.spark.netty

import java.util.Objects

import io.netty.bootstrap.ServerBootstrap
import io.netty.buffer.PooledByteBufAllocator
import io.netty.channel.{ChannelOption, EventLoopGroup}
import io.netty.channel.epoll.{EpollChannelOption, EpollEventLoopGroup, EpollServerSocketChannel}
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.{LogLevel, LoggingHandler}
import io.netty.util.concurrent.DefaultEventExecutorGroup
import org.github.cmonkey.spark.enums.StandardSystemProperty
import org.slf4j.LoggerFactory

class NettyServiceImpl extends NettyService {
  val logger = LoggerFactory.getLogger(classOf[NettyServiceImpl])

  var bossGroup: EventLoopGroup = _
  var workerGroup: EventLoopGroup = _
  var servletExecutor: DefaultEventExecutorGroup = _

  val MAX_THREADS = Runtime.getRuntime.availableProcessors() << 1

  val OS_NAME = "Linux"

  val port = 9999

  def groups(serverBootstrap: ServerBootstrap, workerThreads: Int): Unit = {
    if(Objects.equals(StandardSystemProperty.OS_NAME.value(), OS_NAME)){

      bossGroup = new EpollEventLoopGroup(1)
      workerGroup = new EpollEventLoopGroup(workerThreads)

      serverBootstrap.group(bossGroup, workerGroup)
        .channel(classOf[EpollServerSocketChannel])
        //.option(EpollChannelOption.TCP_CORK,true)
        //.option(ChannelOption.SO_KEEPALIVE, true)
        //.option(ChannelOption.SO_BACKLOG, 100)
        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .handler(new LoggingHandler(LogLevel.INFO))
        //.childHandler()
    }else{

      bossGroup = new NioEventLoopGroup(1)
      workerGroup = new NioEventLoopGroup(workerThreads)

      serverBootstrap.group(bossGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        //.option(ChannelOption.SO_BACKLOG, 100)
        //.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100)
        //.option(ChannelOption.SO_KEEPALIVE, true)
        //.option(ChannelOption.TCP_NODELAY, true)
        .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
        .handler(new LoggingHandler(LogLevel.INFO))
        //.childHandler()
    }
  }

  override def start(): Unit = {

    servletExecutor = new DefaultEventExecutorGroup(MAX_THREADS)

    val serverBootstrap = new ServerBootstrap()

    groups(serverBootstrap, MAX_THREADS << 1)

    serverBootstrap.bind(port).sync()
  }

  override def stop(): Unit = {

    if(null != bossGroup){
      bossGroup.shutdownGracefully().await()
    }

    if(null != workerGroup){
      workerGroup.shutdownGracefully().await()
    }

    if(null != servletExecutor){
      servletExecutor.shutdownGracefully().await()
    }
  }
}
