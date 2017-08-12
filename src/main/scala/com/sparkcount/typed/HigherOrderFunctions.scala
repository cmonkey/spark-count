package com.sparkcount.typed

class Decorator(left: String, right: String){
  def layout[A](x: A) = left + x.toString() + right
}

object HigherOrderFunctions{
  def apply(f: Int => String, v: Int) = f(v)

  def main(args: Array[String]): Unit = {

    val decorator = new Decorator("[","]")
    println(apply(decorator.layout,7))
  }
}
