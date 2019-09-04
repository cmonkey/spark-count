package org.github.cmonkey.spark.test

import org.github.cmonkey.spark.actor.BatchActor
import scala.concurrent.ExecutionContext.Implicits.global

object BatchActorTest extends App{
  val batchActor = new BatchActor[String] {
    override def run(items: Seq[String]): Unit = {
      println(items)
    }
  }

  batchActor.send("hello")

  Thread.sleep(1000)

}
