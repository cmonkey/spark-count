package org.github.cmonkey.spark
object Exerclass{

  def main(args: Array[String]): Unit = {
    val left = 2
    val right = 1
    assert(left == right)

    /*
     *result should equal(3)
     *result should ===(3)
     *result shodld be(3)
     *result shouldEqual 3
     *result shouldBe 3
     */

  }
}

class Point(x: Int, y: Int) {
  override def toString(): String = "(" + x + ", " + y +")"
}
