package org.github.cmonkey.spark.immutable
abstract class ArrayList[E] extends List[E]{

  // internal reperesentation

  val elementData: Array[Any]
}
