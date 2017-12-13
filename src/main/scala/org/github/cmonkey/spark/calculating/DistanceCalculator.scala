package org.github.cmonkey.spark.calculating

trait DistanceCalculator {

  def calulateDistanceInKilomenter(juserLocation: Location,
                                   warehouseLocation: Location): Int
}
