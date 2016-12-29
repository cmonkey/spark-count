package com.sparkcount

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object Streaming{

  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[4]").setAppName("Streaming")
    val sc = new StreamingContext(sparkConf, Seconds(1))
    val lines = sc.socketTextStream("localhost", 9999)
    val words = lines.flatMap(_.split(" "))

    val paris = words.map(word => (word, 1))

    val wordCount = paris.reduceByKey(_+_)
    wordCount.print()

    println(s"This wordCount = ${wordCount}")

    sc.start()
    sc.awaitTermination()
  }
}
