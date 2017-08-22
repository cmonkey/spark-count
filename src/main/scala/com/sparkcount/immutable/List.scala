package com.sparkcount.immutable

trait List[E] {

  def size(): Int
  def add(element: E)
  def get(index: Int): E
}
