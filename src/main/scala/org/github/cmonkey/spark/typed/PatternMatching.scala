package org.github.cmonkey.spark.typed
import scala.util.Random

object PatternMatching{

  def main(args: Array[String]):Unit = {

    val x: Int = Random.nextInt(10)

    x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }

    val m = matchTest(3)
    val m1 = matchTest(1)

    println(s"$x")
    println(s"$m")
    println(s"$m1")
  }

  def matchTest(x: Int): String = {
    x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }
  }
}
