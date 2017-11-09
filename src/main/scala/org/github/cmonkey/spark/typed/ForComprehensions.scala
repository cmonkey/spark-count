package org.github.cmonkey.spark.typed
case class UserBase(val name: String, val age : Int)


object ForComprehensions{

  def main(args:Array[String]): Unit = {

    val userBase = List(
      new UserBase("tr", 28),
      new UserBase("ke", 33),
      new UserBase("je", 44),
      new UserBase("de", 23)
      )

      val twentySomethings = for(user <- userBase if(user.age >= 20 && user.age < 30)) yield user.name

      twentySomethings.foreach(name => println(name))

  }
}
