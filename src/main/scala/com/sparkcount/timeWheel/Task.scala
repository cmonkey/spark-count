package com.sparkcount.timeWheel

trait Task extends Runnable {

  def getPeriod(): Long

}
