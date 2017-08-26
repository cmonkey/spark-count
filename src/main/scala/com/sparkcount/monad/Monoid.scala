package com.sparkcount.monad

trait Monoid[A] extends SemiGroup[A]{

  def zero: A

}

object MonoidApp extends App{

  val stringMonoid = new Monoid[String]{

    def op(a1: String, a2: String) = a1 + a2

    def zero = ""

  }


  def listMonoid[A] = new Monoid[List[A]] {
    def op(a1: List[A], a2: List[A]) = a1 ++ a2

    def zero = Nil
  }

  def optionMonoid[A] = new Monoid[Option[A]] {
    def op(a1: Option[A], a2: Option[A]) = a1 orElse a2

    def zero = None

  }
}
