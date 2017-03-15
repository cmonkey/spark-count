package com.sparkcount

import org.apache.spark.sql.SparkSession
//import org.jblas.DoubleMatrix
object GlomSpark{

  def main(args: Array[String]): Unit = {

    val sc = SparkSession.builder.appName("Glom Spark").master("local").getOrCreate().sparkContext

    val dataList = List(50.0, 40.0, 40.0, 70.0)

    val dataRDD = sc.makeRDD(dataList)

    val maxValue = dataRDD.reduce(_ max _)

    println(s"This maxValue = ${maxValue}")

    val maxValueGlom = dataRDD.glom().map((value: Array[Double]) => value.max).reduce(_ max _)

    println(s"Thsi Glom maxValue = ${maxValueGlom}")

    val rowsList = List[List[Double]](
      List(50.0, 40.0, 44.0),
      List(88,44.0, 44.0),
      List(855,0, 55.0, 44.0),
      List(855,0,55.0, 70.0)
      )

    val weights = List(1.0, 0.5,3)
    val rowRDD = sc.makeRDD(rowsList)
    val result = rowRDD.glom().map(value => {
      /*
      val doubleMatrix = new DoubleMatrix(value.map(value => value.toArray))
      val weightMatrix = new DoubleMatrix(1, weights.length, weights.toArray:_*)
      doubleMatrix.mmul(weightMatrix.transpose())
      */
    })

    println(s"This result = ${result.collect().toList}")
  }
}
