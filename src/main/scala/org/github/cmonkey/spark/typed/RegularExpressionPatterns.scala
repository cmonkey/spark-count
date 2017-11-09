package org.github.cmonkey.spark.typed
import scala.util.matching.Regex

object RegexExpressionPatterns{

  val numberPattern: Regex = "[0-9]".r

  def main(args:Array[String]): Unit = {
    numberPattern.findFirstMatchIn("awesomepassword") match {
      case Some(_) => println("Password ok")
      case None => println("password must contain a number")
    }
  }

}
