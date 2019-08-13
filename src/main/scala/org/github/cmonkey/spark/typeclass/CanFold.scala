package org.github.cmonkey.spark.typeclass

trait CanFold[-T, R] {
  def sum(acc: R, elem: T): R
  def zero: R
}
