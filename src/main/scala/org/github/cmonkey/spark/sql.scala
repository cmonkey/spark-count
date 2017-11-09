package org.github.cmonkey.spark
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession._

object Sql{
  def main(args: Array[String]){

    val spark: SparkSession = SparkSession.builder.appName("Sql").master("local").getOrCreate

    spark.udf.register("myUpper", (s: String) => s.toUpperCase)

    val strs = ('a' to 'z').map(_.toString).toList

    //strs.registerTempTable("strs")


  }

}
