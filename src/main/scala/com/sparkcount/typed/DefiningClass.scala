package com.sparkcount.typed

class User

class Point(var x: Int, var y: Int) {
  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString: String = 
    s"($x, $y)"
}

class Point2(var x: Int = 0, var y: Int = 0)

class Point3{
  private var _x = 0
  private var _y = 0
  private val bound = 100

  def x = _x
  def x_= (newValue: Int): Unit = {
    if (newValue < bound) 
      _x 
    else 
      printWarning
  }

  def y = _y
  def y_= (newValue: Int): Unit = {
    if(newValue < bound) 
      _y
    else 
      printWarning
  }
  private def printWarning = println("WARNING: out of bounds")
}

object DefingClass {

  def main(args: Array[String]): Unit = {
    val user1 = new User

    val point1 = new Point(2, 3)
    point1.x
    println(point1)

    val origin = new Point2
    val point2 = new Point2(1)

    println(point2.x)

    val point3 = new Point3
    point3.x = 99
    point3.y = 101
  }
}
