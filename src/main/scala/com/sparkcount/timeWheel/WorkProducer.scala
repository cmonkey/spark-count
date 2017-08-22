package com.sparkcount.timeWheel

class WorkProducer(val pointor: Int, val slot: Slot[AbstractTask]) extends Runnable{
  override def run() = {

    val task = slot.getTask(pointor)

    task match {
      case Some(x) => x.run()
      case None => println("error task ")
    }
  }
}
