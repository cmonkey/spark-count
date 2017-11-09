package org.github.cmonkey.spark.typed
abstract class Device
case class Phone(model: String) extends Device{
  def screenOf = "Turning screen off"
}

case class Computer(model: String) extends Device{
  def scrrenSaverOn = "Truning screen saver on "
}


object MatchingOnTypeOnly{

  def goIdle(device: Device) = device match {
    case p: Phone => p.screenOf
    case c: Computer => c.scrrenSaverOn
  }
  def main(args: Array[String]): Unit = {

    val p = Phone("phone")
    val c = Computer("computer")

    println(goIdle(p))
    println(goIdle(c))
  }
}
