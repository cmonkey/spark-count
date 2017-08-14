package com.sparkcount.typed

case class User(id:Int, email:String)

object OptionType {

  def getEmail(id:Int):Option[User] = {

    if(id == 10){
      Some(User(1,"test@gmail.com"))
    }else{
      None
    }
  }

  def main(args: Array[String]): Unit = {
    val email = getEmail(1)

    email match{
      case Some(x) => println(x)
      case None => println("none")
    }
  }
}
