package com.sparkcount

import org.apache.spark.sql.SparkSession

object IndexingJsonLogs{

  def main(args: Array[String]) = {

    val connInfoMap = Map("url" -> "jdbc:mysql://10.204.43.88:3306/demo",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "us2004_companies",
      "user" -> "root",
      "password" -> "root"
    )

    val session = SparkSession.builder().appName("Indexing Json").master("local").getOrCreate()

    val df = session.read.format("jdbc").options(connInfoMap).load()

    df.printSchema()

    df.createTempView("comp")

    println(session.sql("select * from comp").count())
    session.sql("select id, url, objects from comp").createTempView("temp")

    val tempCount = session.sql("select * from temp").count()
    println(tempCount)
    session.sql("select * from temp").show(tempCount.toInt)

    session.sql("select * from temp").printSchema()

  }
}
