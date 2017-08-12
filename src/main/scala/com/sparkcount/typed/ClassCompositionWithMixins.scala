package com.sparkcount.typed

abstract class A {
  val message: String
}

class B extends A {
   val message = "I' m an instance of class B"
}

trait C extends A {
  def loudMessage = message.toUpperCase()
}

class D extends B with C 

object ClassCompositionWithMixins{

  def main(args: Array[String]): Unit = {
    val d = new D

    println(s"d.message = $d.message")
    println(s"d.loudMessage = $d.loudMessage")
  }
}
