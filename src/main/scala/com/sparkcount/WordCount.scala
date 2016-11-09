package com.sparkcount

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.spache.spark.SparkConf

object SparkWordCount{
  def main(args: Array[String]){
    // create Spark context with Spark configuration

    val sc = new SparkContext(new SparkConf().setAppName("Spark Count"))

    // get threshold
    val threshold = args(1).toInt

    // read in text file and split each document into words
    val tokenized = sc.textFile(args(0)).flatMap(_.split(" "))

    // count the occurrence of each word
    val wordCount = tokenized.amp((_, 1)).reduceByKey(_ + _)

    // filter out words with fewer than threshold occurrences
    val filtered = wordCount.filter(_._2 >= threshold)

    // count characters
    val charCounts = filtered.flatMap(_._1.toCharArray).map((_,1)).reduceByKey(_+_)

    System.out.println(characters.collect().mkString(", "))
  }
}
