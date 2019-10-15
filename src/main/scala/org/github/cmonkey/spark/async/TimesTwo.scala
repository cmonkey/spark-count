package org.github.cmonkey.spark.async
import scala.concurrent.ExecutionContext.global


object TimesTwo {

    type Async[A] = (A => Unit) => Unit

    def timesTwo(n: Int): Async[Int] =
      onFinish => {
        global.execute(() => {

          val result = n * 2
          onFinish(result)
        })
    }

  def main(args: Array[String]): Unit = {

    timesTwo(20) { result => println(s"Result : $result") }
  }

}
