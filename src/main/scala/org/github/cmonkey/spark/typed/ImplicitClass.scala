package org.github.cmonkey.spark.typed
object Helpers {

  implicit class IntWithTimes(x: Int){
    def times[A](f: => A): Unit = {
      def loop(current: Int): Unit = {
        if (current > 0){
          f
          loop(current - 1)
        }
      }

      loop(x)
    }
  }

}

object ImplicitClassApp extends App{

  import Helpers._

  5 times println("Hi")
}
