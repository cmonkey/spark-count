package org.github.cmonkey.spark

import scala.collection.mutable

object depthPirstSearch{

  /*
  def depthFirstSearch[N](start: N, succ: N => Traversable[N]) = new Iterable[N] {
    def iterator = new Iterator[N]{
      private[this] val s = mutable.ArrayStack[N](start);
      def hasNext = !s.isEmpty
      def next() = {
        val curr = s.pop()
        for (x <- succ(curr)) s.push(x)

        curr
      }
    }
  }
  */
  def main(args: Array[String]): Unit = {
  }
}
