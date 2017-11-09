package org.github.cmonkey.spark
/*
import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class StudentActor extends Actor{
  val path = "akka.tcp://remote-system@127.0.0.1:4999/user/teacherActor"

  val remoteServerRef = context.actorSelection(path)

  def receive = {
    case res:String =>{
      println (res)
    }
    case time:Long =>{
      remoteServerRef ! "历史上规模最大的总筹行动是什么？"
    }
  }
}

object StudentSimulator extends App{
  val config = ConfigFactory
  .parseResources("lietal.conf")
  .getConfig("RemoteClientSideActor")

  val actorSystem = ActorSystem("StudentClient", config)

  val studentActor = actorSystem.actorOf(Props[StudentActor])

  while(true){
    studentActor ! 7.toLong
    Thread.sleep(5000)
  }

}
*/
