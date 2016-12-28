package com.sparkcount

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object EvaluationSparkRDD{

  def main(args: Array[String]): Unit = {

    val sc = SparkSession.builder.appName("EvaluationSparkRdd").master("local").getOrCreate().sparkContext

    val sum = sc.doubleAccumulator

    val squaredSum = sc.doubleAccumulator
    val values = 0 to 100000
    val inputRDD = sc.makeRDD(values)
    val sumRDD = inputRDD.map(value => {
      sum.add(value)
      squaredSum.add(value * value)
    })
    //lazy
    //sumRDD.collect()

    evaluate(sumRDD)

    println("sum is " + sum.value + " and square sum is " + squaredSum.value)
  }

  def evaluate[T](rdd: RDD[T]): Unit = {
    rdd.sparkContext.runJob(rdd,(iter: Iterator[T]) => {
      while(iter.hasNext) iter.next()
    })
  }
}
