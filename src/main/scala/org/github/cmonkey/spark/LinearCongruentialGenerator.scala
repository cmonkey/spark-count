package org.github.cmonkey.spark

class LinearCongruentialGenerator(input:Long){
  var state = input
  
  def nextDouble() = {
    state = 2862933555777941757L * state + 1
    ((state >>> 33).asInstanceOf[Int] + 1).toDouble // / (0x1.0p31)
  }

}
