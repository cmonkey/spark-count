package com.sparkcount

import org.apache.spark.sql.SparkSession

object WordcountDatasetAPI{

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder.master("local").appName("workcountDatasetApi").getOrCreate()

    import sparkSession.implicits._
    val data = sparkSession.read.text("src/main/resources/data.txt").as[String]

    val words = data.flatMap(value => value.split("\\s+"))
    val groupedWords = words.groupByKey(_.toLowerCase)

    val counts = groupedWords.count()
    println(s"This groupedWords count ${counts.show()}")
  }
}
