package com.sparkcount

import scala.concurrent.Future
import scala.util.Random

object RandomService{
  import scala.concurrent.ExecutionContext.Implicits.global

  def performComputations: Future[Int] = Future {
    val number = Random.nextInt(5)
    Thread.sleep(number.toLong + 1)
    number
  }
}
