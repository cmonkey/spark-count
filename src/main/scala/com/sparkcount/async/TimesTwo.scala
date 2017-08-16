package com.sparkcount.async

import scala.concurrent.ExecutionContext.global


object TimesTwo {

    type Async[A] = (A => Unit) => Unit

    def timesTwo(n: Int): Async[Int] =
      onFinish => {
        global.execute(new Runnable {
          def run(): Unit = {

            val result = n * 2
            onFinish(result)
          }

        })
    }

  def main(args: Array[String]): Unit = {

    timesTwo(20) { result => println(s"Result : $result") }
  }

}
