package org.github.cmonkey.spark.disruptor

import java.util.concurrent.{ArrayBlockingQueue, RejectedExecutionHandler, ThreadPoolExecutor, TimeUnit}

object JavaArrayBlockingQueue extends App{

  val CAPACITY = 10

  val service = new ThreadPoolExecutor(4,
    4,
    0L,
    TimeUnit.MILLISECONDS,
    new ArrayBlockingQueue[Runnable](CAPACITY),
    new RejectedExecutionHandler {
      override def rejectedExecution(r: Runnable, executor: ThreadPoolExecutor): Unit = {
        println("reject Execution");
      }
    }
  )

}
