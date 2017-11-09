package org.github.cmonkey.spark.level
case class UserInfo(firstName: String, lastName: String, score: Int)

trait User {
  def name: String
}

class FreeUser(val name: String) extends User

class PremlumUser(val name: String) extends User

object FreeUser{
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}

object PremiumUser{
  def unapply(user: PremlumUser): Option[String] = Some(user.name)
}

