package org.github.cmonkey.spark.atomic

import scala.collection.immutable.Queue

object RefAnyApp extends App{

  val ref = new RefAny(2)
  val transformAndGet = ref.transformAndGet(x => x + 2)
  println(s"transformAndGet = ${transformAndGet}")

  val refQueue = new RefAny(Queue.empty[String])

  val transformAndGetQueue =  refQueue.transformAndGet(q => q.enqueue("Alex"))

  println(s"transformAndGetQueue = ${transformAndGetQueue}")

  val refNumeric = new RefAny(BigInt(1))

  println(s"incrementAndGet = ${refNumeric.incrementAndGet}")

}
