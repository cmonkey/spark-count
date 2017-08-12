package com.sparkcount.typed

import scala.util.Random

object PatternMatching{

  def main(args: Array[String]) = {

    val x: Int = Random.nextInt(10)

    x match {
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }

    matchTest(3)
    matchTest(1)
  }

  def matchTest(x: Int): String = {
    x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }
  }
}
