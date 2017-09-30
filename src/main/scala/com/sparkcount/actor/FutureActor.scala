package com.sparkcount.actor

import com.sparkcount.RandomService
import akka.actor.Actor

object FutureActor{
  case object Request
  case object Response

  case object GetState
  case class State(received: Int = 0, processed: Int = 0)
}

class FutureActor extends Actor{
  import FutureActor._
  import context.dispatcher

  var state = State()

  def receive = {
    case Request => 
      state = state.copy(received = state.received + 1)

      RandomService.performComputations.foreach {_ =>
        state = state.copy(processed = state.processed + 1)
        sender() ! Response
      }

    case GetState => sender() ! state
  }
}
