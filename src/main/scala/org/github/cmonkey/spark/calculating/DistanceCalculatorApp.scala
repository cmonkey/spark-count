package org.github.cmonkey.spark.calculating

object DistanceCalculatorApp extends App{

  val r = new DistanceCalculatorImpl().calulateDistanceInKilomenter(Location(10, 20),
    Location(40,30))

  println(s"$r")

}
