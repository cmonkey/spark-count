package org.sparkcount

import org.apache.spark.sql.SparkSession

object ItasWorkshopKey{

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder.master("local").appName("ItasWorkshopKey").getOrCreate().sparkContext
    val data = 1 to 10000
    val distData = sc.parallelize(data)

    println(s"This distData = ${distData.filter(_ < 10).collect().toList}")
  }
}
