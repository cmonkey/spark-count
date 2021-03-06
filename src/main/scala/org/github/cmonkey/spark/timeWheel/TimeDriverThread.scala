package org.github.cmonkey.spark.timeWheel
import java.util.concurrent.ExecutorService

class TimeDriverThread(val workProducerThread: ExecutorService, val workProducer: WorkProducer ) extends Runnable{
  override def run() = {

    workProducerThread.execute(workProducer)
  }
}
