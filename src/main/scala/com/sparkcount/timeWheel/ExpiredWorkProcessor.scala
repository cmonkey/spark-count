package com.sparkcount.timeWheel

import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent._

class ExpiredWorkProcessor(val threadCount: Int) {

  var workThreads: ExecutorService = null

  def this() = {
    this(10)
    workThreads = Executors.newFixedThreadPool(threadCount,
      new ThreadFactory {
        val count = new AtomicLong(0)

        override def newThread(r: Runnable): Thread = {

          val t = new Thread(new ThreadGroup("TimeWeel"),
            r,
            "ExpiredWorkProcessor_" + count.getAndIncrement()
          )

          t
        }
      })
  }

  def getWorkThreads() = {
    workThreads
  }
}
