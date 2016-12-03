package com.sparkcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

object GroupKeyCheckPoint{
  def main(args: Array[String]){
    val conf = new SparkConf().setAppName("GroupKeyCheckPoint")
    val sc  = new SparkContext(conf)

    val checkpoint = "hdfs:/10.0.0.6:9000/usr/spark/checkpoint"

    sc.setCheckpointDir(checkpoint)

    val data = Array[(Int, Char)](
      (1, 'a'), 
      (2,'b'),
      (3, 'c'), 
      (4, 'd'), 
      (5, 'e'), 
      (3, 'f'), 
      (2, 'g'), 
      (1, 'h') 
      )

    val pairs = sc.parallelize(data, 3)

    pairs.checkpoint
    pairs.count

    val result = pairs.groupByKey(2)

    //result.foreachWith(i => i) ((x, i) => println("[PartitionIndex ]" +  i + "]" + x))

    println(result.toDebugString)

  }
}
