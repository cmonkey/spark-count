package com.sparkcount.typed

case class UserInfo(id:Int, email:String)

object OptionType {

  def getEmail(id:Int):Option[UserInfo] = {

    if(id == 10){
      Some(UserInfo(1,"test@gmail.com"))
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
