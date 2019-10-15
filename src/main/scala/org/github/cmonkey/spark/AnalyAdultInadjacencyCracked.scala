package org.github.cmonkey.spark

import java.util.concurrent.TimeUnit

import org.apache.spark.sql.{Encoders, SparkSession}

object AnalyAdultInadjacencyCracked {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("AnalyAdultInadjacencyCracked")
      .master("local[*]")
      .getOrCreate()

    val file = "src/main/resources/firefox-adult-inadjacency-cracked.txt"

    val lines = spark.read.textFile(file)

    val dataLines = lines.filter(line => !line.startsWith("#"))

    implicit def encoder = Encoders.STRING
    val lastLines = dataLines.map(_.split(":").last).map(line => {
      var r = line
      if(line.endsWith("DEFUNCT")){
        r = line.replaceAll("DEFUNCT", "")
      }
      r
     })

    lastLines.count()

    lastLines.foreach(line => println(line))

    TimeUnit.SECONDS.sleep(10)
  }
}
