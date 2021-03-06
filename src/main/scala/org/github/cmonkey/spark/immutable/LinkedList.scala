package org.github.cmonkey.spark.immutable
abstract class LinkedList[E] extends List[E]{
  // internal representation

  val size: Int

  val first: Node[E]

  val last: Node[E]
}
