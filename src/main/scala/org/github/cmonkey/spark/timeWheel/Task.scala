package org.github.cmonkey.spark.timeWheel
trait Task extends Runnable {

  def getPeriod(): Long

}
