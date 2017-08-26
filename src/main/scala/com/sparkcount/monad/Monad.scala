package com.sparkcount.monad

trait Monad[M[_]] {

  def unit[A](a: A): M[A]

  def join[A](mma: M[M[A]]): M[A]
}
