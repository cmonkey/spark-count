package org.github.cmonkey.spark
import java.lang.Math


object MethodsFunction{

  def main(args: Array[String]): Unit = {
  }

  def exists[T] (xs: Array[T], p: T => Boolean) = {
    var i: Int = 0
    while(i < xs.length && !p(xs(i)) ){
      i = i + 1
      i < xs.length
    }
  }

  def sqrts(xs: List[Double]) : List[Double] = {
    xs.filter(_ <= 0).map(x => Math.sqrt(x))
  }

}
