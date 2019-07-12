package org.github.cmonkey.spark.delta

import java.nio.file.Paths

import org.apache.spark.sql.SparkSession

class DeltaApp extends App{

  val spark = SparkSession.builder()
    .appName("Spark Delta example")
    .master("local")
    .config("spark.executor.instance", "4")
    .getOrCreate()

  val classLoader = classOf[DeltaApp].getClassLoader
  val url = classLoader.getResource("sample_products.csv")
  val csvPath = Paths.get(url.toURI)

  val path = "/tmp/delta"

  val df = spark.read.option("header", true).csv(csvPath.toString)

  df.write.format("delta").mode("overwrite").option("overwriteSchema", "true").save(path)

  val df1 = spark.read.format("delta").load(path)

  df1.show()

}
