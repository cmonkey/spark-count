package com.sparkcount

object RandomScala{

  def main(args: Array[String]): Unit = {
    print("Hello world\n")

    val primes: Stream[Int] = {

      def generatePrimes (s: Stream[Int]): Stream[Int] = 
        s.head #:: generatePrimes(s.tail filter (_ % s.head != 0))
      generatePrimes(Stream.from(2))
    }

    println(primes.filter(_ > 100).take(20).toVector)
  }
}
