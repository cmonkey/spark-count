package org.github.cmonkey.spark.typed
abstract class A{
  val message: String
}

class B extends A {
   val message = "I' m an instance of class B"
}

trait C extends A {
  def loudMessage = message.toUpperCase()
}

class D extends B with C 

abstract class AbsIterator{
  type T
  def hasNext: Boolean
  def next(): T
}

class StringIterator(s: String) extends AbsIterator{
  type T = Char
  private var i = 0
  def hasNext = i < s.length
  def next() = {
    val ch = s charAt i 
    i += 1
    ch
  }
}

trait RichIterator extends AbsIterator{
  def foreach(f: T => Unit): Unit = 
    while (hasNext)
      f(next())
}


object ClassCompositionWithMixins{

  def main(args: Array[String]): Unit = {
    val d = new D

    println(s"d.message = $d.message")
    println(s"d.loudMessage = $d.loudMessage")

    val param = if(args.isEmpty) 10 else args(0)

    class Iter extends StringIterator(param.toString) with RichIterator
    val iter = new Iter
    iter foreach println

  }
}
