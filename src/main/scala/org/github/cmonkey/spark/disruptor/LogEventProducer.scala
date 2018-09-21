package org.github.cmonkey.spark.disruptor

import com.lmax.disruptor.{EventTranslatorOneArg, RingBuffer}

class LogEventProducer(ringBuffer:RingBuffer[LogEvent]) {

  val TRANSLATOR = new EventTranslatorOneArg[LogEvent, String] {
    override def translateTo(event: LogEvent, sequence: Long, arg0: String): Unit = {
      event.msg = arg0
    }
  }

  def onData(msg:String): Unit = {
    ringBuffer.publishEvent(TRANSLATOR, msg)
  }

}
