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


object  Main{

  def main(args: Array[String]) = {
    //FreeUser.unapply(new FreeUser"hi")
  }
  def advance(xs: List[UserInfo]) = xs match {

    case UserInfo(_, _, score1) :: UserInfo(_, _, score2) :: _ => score1 - score2
    case _ => 0
  }
}


