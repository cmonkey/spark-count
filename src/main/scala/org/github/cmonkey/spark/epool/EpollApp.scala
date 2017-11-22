package org.github.cmonkey.spark.epool

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.epoll.{EpollEventLoopGroup, EpollServerSocketChannel}
import io.netty.channel.socket.SocketChannel
import io.netty.handler.logging.{LogLevel, LoggingHandler}

object EpollApp extends App{

  val serverBootstrap = new ServerBootstrap()

  //val bootLoopGroup = new NioEventLoopGroup()
  //val workerLoopGroup = new NioEventLoopGroup(1)
  val bootLoopGroup = new EpollEventLoopGroup()
  val workerLoopGroup = new EpollEventLoopGroup(1)

  serverBootstrap.group(bootLoopGroup, workerLoopGroup)
      .channel(classOf[EpollServerSocketChannel])
    .childHandler(new ChannelInitializer[SocketChannel] {
      override def initChannel(c: SocketChannel) = {
        c.pipeline()
          .addLast(new LoggingHandler(LogLevel.INFO))
      }
    })

  val future = serverBootstrap.bind(9999).sync()

  val channel = future.channel()

}
