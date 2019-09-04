package org.github.cmonkey.spark.actor

import scala.collection.mutable
import scala.concurrent.ExecutionContext

abstract class BatchActor[T]()(implicit ec: ExecutionContext){
  private val queue = new mutable.Queue[T]()
  private var scheduled = false

  def send(t: => T) = synchronized{
    queue.enqueue(t)
    if(!scheduled){
    scheduled = true
    ec.execute(() => runWithItems())
    }
  }

  def run(items: Seq[T]): Unit 

  def runWithItems(): Unit ={
    val items = synchronized(queue.dequeueAll(_ => true))
    try {
      run(items)
    }catch{
      case e: Throwable => e.printStackTrace()
    }

    synchronized{
      if(queue.nonEmpty) {
        ec.execute(() => runWithItems())
      }else{
        assert(scheduled)
        scheduled = false
      }
    }
  }
}
