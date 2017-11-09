package org.github.cmonkey.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object CoreAbstractions {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CoreAbstractions").setMaster("local[*]")

    val sparkSession = SparkSession.builder().config(conf).getOrCreate()

    val sc = sparkSession.sparkContext

    val rdd = sc.parallelize(Seq(1,2,3))

    println(rdd.dependencies)

    println(rdd.toDebugString)

    val rdd2 = rdd.map(_.toString)

    println(rdd2.dependencies)

    println(rdd2.toDebugString)

    import sparkSession.implicits._
    val peopleDataSet = sparkSession.read.json("src/main/resources/people.json").as[CorePeople]

    peopleDataSet.cache()

    val eldersDataset = peopleDataSet.filter(_.age.exists(_ > 65))

    eldersDataset.write.save(s"src/main/resources/core-people-${System.currentTimeMillis()}")
    eldersDataset.explain(extended = true)
    eldersDataset.queryExecution.debug.codegen()
  }

}

case class CorePeople(name:String, age: Option[Long])
