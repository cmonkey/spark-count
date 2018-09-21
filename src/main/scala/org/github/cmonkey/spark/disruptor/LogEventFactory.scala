package org.github.cmonkey.spark.disruptor

import com.lmax.disruptor.EventFactory

class LogEventFactory extends EventFactory[LogEvent] {

  override def newInstance(): LogEvent = {

    new LogEvent("msg")
  }

}
