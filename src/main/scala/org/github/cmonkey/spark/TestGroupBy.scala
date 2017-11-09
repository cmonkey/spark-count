package org.github.cmonkey.spark
import java.util.Random

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._

object TestGroupBy{
  def main(args: Array[String]){
    val conf        = new SparkConf().setAppName("Test GroupBy").setMaster("local[*]")

    var numMappers  = 100
    var numKVPairs  = 10000
    var valSize     = 1000
    var numReducers = 36

    val sc          = new SparkContext(conf)

    val pairs1 = sc.parallelize(0 until numMappers, numMappers).flatMap{p => 
          val ranGen = new Random
          var arr1 = new Array[(Int, Array[Byte])](numKVPairs)

          for(i <- 0 until numKVPairs){
            val byteArr = new Array[Byte](valSize)
            ranGen.nextBytes(byteArr)
            arr1(i) = (ranGen.nextInt(Int.MaxValue), byteArr)
          }
          arr1
        }.cache

    pairs1.count

    println(pairs1.groupByKey(numReducers).count())

    sc.stop()

  }
}
