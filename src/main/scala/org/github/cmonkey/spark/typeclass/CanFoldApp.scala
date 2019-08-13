package org.github.cmonkey.spark.typeclass

object CanFoldApp extends App {
  implicit object CanFoldInts extends CanFold[Int, Long]{
    override def sum(acc: Long, elem: Int) = acc + elem

    override def zero = 0
  }
  def sum[A,B](list: Traversable[A])(implicit adder: CanFold[A, B]): B =
    list.foldLeft(adder.zero)((acc,e) => adder.sum(acc, e))

  sum(1 :: 2 :: 3 :: Nil)
}

