package org.github.cmonkey.spark.actor
import akka.actor.{Actor, ActorRef}

import Actor._


class ChainActor extends Actor {

  def receive = {
    case "s" => println("Hello s")
  }
  def test(): Unit = {
    println("Hello Actor")
  }
}

object TestActor{

  def main(args: Array[String]):Unit = {
    val chainActor = new ChainActor

    chainActor.preStart()
  }
}

