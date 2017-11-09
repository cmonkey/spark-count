package org.github.cmonkey.spark

import org.apache.spark.{SparkException, SparkUserAppException}
import org.apache.spark.sql.SparkSession


object DemystifyingSparkSerializationError{

  def handleException(se: Exception) = println(se)

  def main(args: Array[String]): Unit = {
    class Value

    case class Wrapper(v: Value)
    val sc = SparkSession.builder().appName("DemystifyingSparkSerializationError").master("local[*]").getOrCreate().sparkContext

    val value = Wrapper(new Value)

    try {
      sc.parallelize(1 to 10).map(_ => value).foreach(println)
    }catch{
      case se: SparkException => handleException(se)
      case ex: Exception => handleException(ex)
    }

    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).foreach(println)

    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).count()

    sc.parallelize(1 to 10).map(_ => Wrapper(new Value)).map(_.toString).reduce(_+_)

  }
}