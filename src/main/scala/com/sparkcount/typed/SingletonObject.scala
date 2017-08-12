package com.sparkcount.typed

object Blah {
  def sum(l: List[Int]): Int = l.sum
}

class IntPair(val x: Int, val y: Int)

object IntPair{
  import math.Ordering
  implicit def ipord: Ordering[IntPair] = 
    Ordering.by(ip => (ip.x, ip.y))
}

object SingletonObject {

}
