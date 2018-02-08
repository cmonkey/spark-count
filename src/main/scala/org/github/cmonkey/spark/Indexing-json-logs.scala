package org.github.cmonkey.spark

import org.apache.spark.sql.SparkSession

object IndexingJsonLogs{

  def main(args: Array[String]){

    val domain = ""
    val jdbcUrl = s"jdbc:mysql://${domain}:3306/insurance?useSSL=false"
    val driver = "com.mysql.cj.jdbc.Driver"
    val dbtable = "customer"
    val dbUserName = "root"
    val dbPwd = ""

    val connInfoMap = Map("url" -> jdbcUrl,
      "driver" -> driver,
      "dbtable" -> dbtable,
      "user" -> dbUserName,
      "password" -> dbPwd
    )

    val session = SparkSession.builder().appName("Indexing Json").master("local").getOrCreate()

    val df = session.read.format("jdbc").options(connInfoMap).load()

    df.printSchema()

    df.createTempView("customer")

    println(session.sql("select * from customer").count())
    session.sql("select * from customer").createTempView("temp")

    val tempCount = session.sql("select * from temp").count()
    println(tempCount)
    session.sql("select * from temp").show(tempCount.toInt)

    session.sql("select * from temp").printSchema()

    val catalog = session.catalog

    catalog.listDatabases().select("name").show()

    catalog.listTables().select("name").show()

    df.cache()
    println(catalog.isCached("temp"))
    println(catalog.isCached("customer"))

    catalog.listFunctions().select("name", "description", "className", "isTemporary").show()

  }
}
