package org.github.cmonkey.spark.disruptor

import com.lmax.disruptor.YieldingWaitStrategy
import com.lmax.disruptor.dsl.{Disruptor, ProducerType}
import com.lmax.disruptor.util.DaemonThreadFactory

object LogEventApp extends App{

  val factory = new LogEventFactory

  val bufferSize = 1024

  val disruptor = new Disruptor[LogEvent](factory,
    bufferSize,
    DaemonThreadFactory.INSTANCE,
    ProducerType.SINGLE,
    new YieldingWaitStrategy()
  )

  disruptor.handleEventsWith(new LogEventConsumer)

  disruptor.start()

  val ringBuffer = disruptor.getRingBuffer

  val producer = new LogEventProducer(ringBuffer)

  for(i <- 0 until 100000000){
    producer.onData("msg-"+i)
  }

}
