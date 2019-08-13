package org.github.cmonkey.spark.atomic

import java.util.concurrent.atomic.AtomicReference

class RefAny[T](initial: T) extends Ref[T]{

  private[this] val instance = new AtomicReference(initial)

  override def get: T = instance.get()

  override def set(update: T): Unit = instance.set(update)

  override def compareAndSet(expect: T, update: T): Boolean = instance.compareAndSet(expect, update)

  override def transformAndGet(cb: T => T): T = ???
}
