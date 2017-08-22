package com.sparkcount.immutable

abstract class Node[E] {

  val item: E

  val next: Node[E]

  val prev: Node[E]

}
