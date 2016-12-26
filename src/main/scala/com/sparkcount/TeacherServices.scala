import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor{
  def receive = {
    case "1+1等于多少?" =>{
      val originalSender = sender;
      sender !  "1=1等于2"
    }
    case "历史上规模最大的总筹行动是什么?" => {
      val originalSender = sender;
      sender ! "历史上规模最大的总筹行动是 + 1s"
    }
    case "腾讯第一网红是谁?" =>{
      val originalSender = sender;
      sender ! "腾讯第一网红是\"我去\""
    }
    case _ => {
      val originalSender = sender;
      sender ! "这个问题， 老师也得查查书"
    }
  }
}

object TeacherServices extends App{
  val config = ConfigFactory
  .parseResources("lietal.conf")
  .getConfig("RemoveServerSideActor")

  val system = ActorSystem("TeacherService", config)

  system.actorOf(Props(TeacherActor), "teacherActor")
}
