package org.github.cmonkey.spark.typed
sealed abstract class Furniture
case class Couch() extends Furniture
case class Chair() extends Furniture

object SealedClasses{

  def findPlaceToSit(piece: Furniture): String = piece match {
    case a: Couch => "lie on the couch"
    case b: Chair => "sit on the chair"
  }
  def main(args: Array[String]): Unit = {

    val couch = Couch()
    val chair = Chair()

    println(findPlaceToSit(couch))
    println(findPlaceToSit(chair))
  }
}
