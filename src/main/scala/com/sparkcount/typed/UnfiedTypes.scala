package com.sparkcount.typed

object UnfiedTypes{

  def main(args: Array[String]): Unit = {
    val list: List[Any] = List(
      "a string",
      732,
      'c',
      true,
      () => "an anonymous function returning a string"
    )
    list.foreach(element => println(element))

    val x: Long = 987654321
    val y: Float = x

    val face: Char = '@'
    val number: Int = face

    println(number)

    val a: Long = 987654321
    val b: Float = a
    val c: Long = b.toLong
  }
}
