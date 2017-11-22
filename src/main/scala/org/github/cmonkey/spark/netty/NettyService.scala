package org.github.cmonkey.spark.netty

trait NettyService {

  @throws(classOf[InterruptedException])
  def start()

  def stop()

}
