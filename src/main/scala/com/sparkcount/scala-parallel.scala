package com.sparkcount

object Parallel{
  def time(cont: Int)(call : => Unit): Long = {
    val start = System.currentTimeMillis

    (1 to cont) foreach (_ => call)

    System.currentTimeMillis - start

  }
  def main(args: Array[String]): Unit = {
    val xs = List.range(0, 1000)

    println(time(10){xs map timeConsumedMap})

    println(time(10){xs.par map timeConsumedMap})
  }

  def timeConsumedMap(x: Int): Int = {
    Thread.sleep(1)
    x * 2
  }
}
