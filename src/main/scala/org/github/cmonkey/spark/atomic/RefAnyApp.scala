package org.github.cmonkey.spark.atomic

object RefAnyApp extends App{

  val ref = new RefAny(2)
  val transformAndGet = ref.transformAndGet(x => x + 2)
  println(s"transformAndGet = ${transformAndGet}")
}
