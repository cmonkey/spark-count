package org.github.cmonkey.spark.atomic

trait Ref[T] {
  def get: T
  def set(update: T)
  def compareAndSet(expect: T, update: T): Boolean

  def transformAndGet(cb: T => T): T
}

object Ref{
  def apply[T](initial: T) = new RefAny(initial)
}
