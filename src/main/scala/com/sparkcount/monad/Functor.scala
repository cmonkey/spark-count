package com.sparkcount.monad

trait Functor [F[_]]{

  def map[A,B](a: F[A]) (f: A => B): F[B]

}

object FunctorApp extends App{
  def listFunctor = new Functor[List] {
    def map[A, B](a: List[A])(f: (A) => B) = a.map(f)
  }

  val s = Some(1)

  val ss = s.map(i => Some(i + 1))

  ss.flatten

  val sf = s.flatMap(i => Some(i + 1))
}
