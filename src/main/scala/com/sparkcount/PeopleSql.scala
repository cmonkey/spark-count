package com.sparkcount

import org.apache.spark.sql.SparkSession

case class People(name: String, age: Int)

object PeopleSql{

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder().appName("PeopleSql").master("local[4]").getOrCreate()

    import sparkSession.implicits._
    val people = sparkSession.read.text("src/main/resources/people.txt").as[String].map(_.split(",")).map(p => People(p(0), p(1).trim.toInt))

    people.createTempView("people")

    val r = sparkSession.sql("select * from people where age > 40 and age < 60")

    r.map(t => "Name:" + t(0)).collect().foreach(println)

    r.show()
  }
} 
