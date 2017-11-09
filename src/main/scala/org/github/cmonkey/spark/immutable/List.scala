package org.github.cmonkey.spark.immutable
trait List[E] {

  def size(): Int
  def add(element: E)
  def get(index: Int): E
}
