package com.sparkcount.actor

import java.io.File

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class RemoteActor extends Actor{

  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
      sender ! "hi"
    }

    case _ => println("Received unknown msg")
  }
}

object RemoteActor{

  def main(args: Array[String]): Unit = {
    val configFile = getClass.getClassLoader.
    getResource("remote_application.conf").getFile

    val config = ConfigFactory.parseFile(new File(configFile))

    val system = ActorSystem("RemoteSystem", config)

    val remote = system.actorOf(Props[RemoteActor], name="remote")

    println("remote is ready")
  }
}
