package com.sparkcount

import scala.util.Try
import scala.util.control.Exception._

object MyMethod extends App{

  def myMethod(s: String): Try[Int] = {
    Try(s.toInt)
  }

  val m = myMethod("30")

  println(s"($m.getOrElse(10))")

  m.toOption match {
    case None => println("no")
    case _ => println(m.get)
  }

  allCatch.opt(3)

  allCatch.opt(0/0)

  allCatch.either(3)

  allCatch.either(0/0)

  allCatch.withTry(0/0)

  noCatch.withTry(0/0)
}
