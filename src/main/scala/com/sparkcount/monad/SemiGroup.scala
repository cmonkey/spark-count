package com.sparkcount.monad

trait SemiGroup[A] {

  def op(a1: A, a2: A): A

}
