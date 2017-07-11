package com.sparkcount

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object WordCount{
  def main(args: Array[String]){
    // create Spark context with Spark configuration

    val sc = SparkSession.builder().master("local[*]").appName("spark count").getOrCreate().sparkContext
    //val sc = new SparkContext(new SparkConf().setAppName("Spark Count"))

    // get threshold
    val threshold = 10

    // read in text file and split each document into words
    val tokenized = sc.textFile("README.md").flatMap(_.split(" "))

    // count the occurrence of each word
    val wordCount = tokenized.map((_, 1)).reduceByKey(_ + _)

    // filter out words with fewer than threshold occurrences
    val filtered = wordCount.filter(_._2 >= threshold)

    // count characters
    val charCounts = filtered.flatMap(_._1.toCharArray).map((_,1)).reduceByKey(_+_)

    System.out.println(charCounts.collect().mkString(", "))
  }
}
