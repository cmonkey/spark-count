package org.github.cmonkey.spark
import org.apache.spark.sql.SparkSession

object SparkSessionAPI{

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder.
    master("local")
    .appName("spark-session-example")
    .getOrCreate()

    val sparkSessionHive = SparkSession.builder.
    master("local")
    .appName("spark session example")
    .enableHiveSupport()
    .getOrCreate()

    // Read data using Spark Session 
    val df = sparkSession.read.option("header", "true").
    csv("src/main/resources/sales.csv")

    df.printSchema()
  }
}
