package org.github.cmonkey.spark.atomic

import java.util.concurrent.atomic.AtomicReference

import scala.annotation.tailrec

class RefAny[T](initial: T) extends Ref[T]{

  private[this] val instance = new AtomicReference(initial)

  override def get: T = instance.get()

  override def set(update: T): Unit = instance.set(update)

  override def compareAndSet(expect: T, update: T): Boolean = instance.compareAndSet(expect, update)

  @tailrec
  final override def transformAndGet(cb: T => T): T = {
    val oldValue = get
    val newValue = cb(oldValue)

    if(!compareAndSet(oldValue, newValue)){
      transformAndGet(cb)
    }else{
      newValue
    }
  }

  @tailrec
  final override def getAndTransform(cb: T => T): T = {
    val oldValue = get
    val update = cb(oldValue)

    if(!compareAndSet(oldValue, update)){
      getAndTransform(cb)
    }else{
      oldValue
    }
  }
}
