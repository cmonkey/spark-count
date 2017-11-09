package org.github.cmonkey.spark
import org.apache.spark.sql.SparkSession

object SparkSQLExample{

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("SparkSQLExample").master("local[*]").getOrCreate()

    val sc = sparkSession.sparkContext

    val jsonRDD = sc.wholeTextFiles("src/main/resources/customer.json").map(x => x._2)

    val customer = sparkSession.read.json(jsonRDD)
    //val customer = sparkSession.read.format("json").json("src/main/resources/customer.json")

    customer.printSchema()

    customer.createTempView("customer")

    sparkSession.sql("select 'First Name', Count, County, Sex, Year from customer").show()

    val connMap = Map("url" -> "jdbc:mysql://10.204.43.88:3306/demo",
      "driver" -> "com.mysql.jdbc.Driver",
    "dbtable" -> "us2004_companies",
    "user" -> "root",
    "password" ->  "root")

    val df = sparkSession.read.format("jdbc").options(connMap).load()

    df.printSchema()

    df.show()

    df.createTempView("names")

    df.sqlContext.sql("select url from names").collect().foreach(println)

  }
}
