package com.sparkcount

import org.apache.spark.sql.SparkSession


object DemystifyingSparkSerializationError{

  def main(args: Array[String]): Unit = {
    class Value

    case class Wrapper(v: Value)
    val sc = SparkSession.builder().appName("DemystifyingSparkSerializationError").master("local[*]").getOrCreate().sparkContext

    val value = Wrapper(new Value)
    //sc.parallelize(1 to 10).map(_ => value).foreach(println)


    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).foreach(println)

    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).count()

    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).map(_.toString).reduce(_+_)

  }
}