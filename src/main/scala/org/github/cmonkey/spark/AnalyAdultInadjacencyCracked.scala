package org.github.cmonkey.spark

import org.apache.spark.sql.SparkSession

object AnalyAdultInadjacencyCracked {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("AnalyAdultInadjacencyCracked")
      .master("local[*]")
      .getOrCreate()

    val file = "resources/firefox-adult-inadjacency-cracked.txt"

    val lines = spark.read.textFile(file)

    lines.show(100)
  }
}
