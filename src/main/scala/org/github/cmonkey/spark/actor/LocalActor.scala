package org.github.cmonkey.spark.actor
import java.io.File
/*

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class LocalActor extends Actor{

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    val remoteActor = 
      context.actorSelection("akka.tcp://RemoteSystem@127.0.0.1:5150/user/remote")
        println("That 's remote:" + remoteActor)
        remoteActor ! "hi"
  }

  override def receive: Receive = {
    case msg: String => {
      println("got message from remote" + msg)
    }
  }
}

object LocalActor{

  def main(args: Array[String]): Unit = {
    val configFile = getClass.getClassLoader.getResource("local-configuration.conf").getFile

    val config = ConfigFactory.parseFile(new File(configFile))

    val system = ActorSystem("ClientSystem", config)

    val localActor = system.actorOf(Props[LocalActor], name="local")
  }
}
*/
