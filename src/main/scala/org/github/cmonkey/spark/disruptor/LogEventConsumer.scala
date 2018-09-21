package org.github.cmonkey.spark.disruptor

import com.lmax.disruptor.EventHandler

class LogEventConsumer extends EventHandler[LogEvent]{
  override def onEvent(event: LogEvent, sequence: Long, endOfBatch: Boolean): Unit = {

    println(s"Threade name = ${Thread.currentThread().getName()} and event = ${event}")
  }
}
