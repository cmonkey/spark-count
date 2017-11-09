package org.github.cmonkey.spark

import java.util.UUID

import scala.collection.immutable.HashSet

class IdempotentFiler[Id] private(entries: HashSet[Id], queue: Vector[Id], size: Int){
 self =>
 def query(key: Id): (Boolean, IdempotentFiler[Id]) = {
  if(entries contains key) {
    (true, self)
  } else{
   if(queue.size < size) {
     (false, new IdempotentFiler[Id](entries + key, key +: queue, size))
   }else{
     val oldestKey = queue.last
     (false, new IdempotentFiler[Id](entries - oldestKey + key, key +: queue, size))
   }
  }
 }
}

object IdempotentFiler{
  def apply[A](size: Int): IdempotentFiler[A] = new IdempotentFiler[A](HashSet.empty[A], Vector.empty[A], size)
}

object IdempotentFilterApp extends App {
  val idemFilter = IdempotentFiler[UUID](size = 2)

  val uuidA = UUID.randomUUID()
  val uuidB = UUID.randomUUID()
  val uuidC = UUID.randomUUID()

  val (resultOne, idemFilterOne) = idemFilter.query(uuidA)
  val (resultTwo, idemFilterTwo) = idemFilter.query(uuidB)
  val (resultThree, idemFilterThree) = idemFilter.query(uuidC)

  println(s"one = $resultOne $idemFilterOne")
  println(s"two = $resultTwo $idemFilterTwo")
  println(s"three = $resultThree $idemFilterThree")

}



