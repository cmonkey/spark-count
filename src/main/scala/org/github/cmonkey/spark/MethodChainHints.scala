package org.github.cmonkey.spark

object MethodChainHints{
  val words = Seq("a", "sequence", "of", "words")

  words
  .view
  .map(_.length)
  .filter(_ > 3)
  .toSeq
  .distinct
  .headOption
  .getOrElse(0)
}
