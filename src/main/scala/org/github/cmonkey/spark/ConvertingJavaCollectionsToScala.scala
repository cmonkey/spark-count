package org.github.cmonkey.spark


object ConvertingJavaCollections{

  def main(args: Array[String]): Unit = {
    val javaList = new java.util.ArrayList[Double]()

    javaList.add(10.0)
    javaList.add(20.0)
    javaList.add(40.0)

    //javaList.map(value => value * 10)

    /*
    val scalaList = javaList.asScala

    val sum = scalaList.sum

    val squareList = scalaList.map(value => value * value)
    println("sum is " + sum)
    println("square list is " + squareList)

    val javaHashMap = new java.util.HashMap[String, Double]()
    javaHashMap.put("jack", 1000)
    javaHashMap.put("bob", 2000)

    val scalaMap = javaHashMap.asScala
    println(scalaMap.getOrElse("dummy", 0))
    println(scalaMap.map(value => (value._1, value._2 * value._2)))

    val jList = scalaList.asJava
    println(jList.getClass.getName)
    */
  }
}
