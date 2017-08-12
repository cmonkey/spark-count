package com.sparkcount.typed

case class User(val name: String, val age : Int)


object ForComprehensions{

  def main(args:Array[String]): Unit = {

    val userBase = List(
      new User("tr", 28),
      new User("ke", 33),
      new User("je", 44),
      new User("de", 23)
      )

      val twentySomethings = for(user <- userBase if(user.age >= 20 && user.age < 30)) yield user.name

      twentySomethings.foreach(name => println(name))

  }
}
