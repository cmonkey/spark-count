package com.sparkcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession

/**
  * Created by cmonkey on 12/22/16.
  */
object SparkCoding {

  def main(args: Array[String]): Unit = {


    val sparkSession = SparkSession.builder().master("local[*]").appName("SparkConding").getOrCreate()

    val sc = sparkSession.sparkContext

    sc.parallelize(List(1,2,3,4,5,6)).map(_ * 2).filter(_ > 5).collect()

    val rdd = sc.parallelize(List(1,2,3,4,5,6,7,8,9,10))
    rdd.reduce(_ + _)

    val rdd1 = sc.parallelize(List(("a", 1), ("a", 2), ("b", 1), ("b", 3)))

    val rdd2 = sc.parallelize(List(("a", 3), ("a", 4), ("b", 1), ("b", 2)))

    val unionRDD = rdd1.union(rdd2)

    unionRDD.collect()

    val intersectionRDD = rdd1.intersection(rdd2)

    intersectionRDD.collect()

    val joinRDD = rdd1.join(rdd2)

    joinRDD.collect()

    rdd1.lookup("a")

    unionRDD.lookup("a")

    joinRDD.lookup("a")
  }

}
