package org.github.cmonkey.spark

import scala.util.{Failure, Success, Try}
import scala.util.control.Exception._

object MyMethod extends App{

  def myMethod(s: String): Try[Int] = {
    Try(s.toInt)
  }

  def handleException = {
    Try{
      10 / 2
    }match {
      case Success(result) => println(result)
      case Failure(exception) => exception.printStackTrace()
    }
  }

  val m = myMethod("30")

  println(s"($m.getOrElse(10))")

  m.toOption match {
    case None => println("no")
    case _ => println(m.get)
  }

  handleException

  allCatch.opt(3)

  allCatch.opt(0/0)

  allCatch.either(3)

  allCatch.either(0/0)

  allCatch.withTry(0/0)

  noCatch.withTry(0/0)
}
