package org.github.cmonkey.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.types._

object PortingCodefromRDDAPItoDatasetAPI{

  def main(args: Array[String]): Unit = {

    //val sparkConf = new SparkConf().setAppName("ProtingCode").setMaster("local")
    //val sparkContext = new SparkContext(sparkConf)

    val sparkSession = SparkSession.builder().appName("ProtingCode").master("local").getOrCreate()

    val sparkContext = sparkSession.sparkContext

    val rdd = sparkContext.textFile("src/main/resources/data.txt")

    import sparkSession.implicits._
    val ds = sparkSession.read.text("src/main/resources/data.txt").as[String]

    // Calculating count 

    // RDD
    println("Count ")
    println(rdd.count())

    // Dataset
    println(ds.count())

    // WordCount Example 

    // RDD

    println(" wordcount ")

    val wordsRDD = rdd.flatMap(value => value.split("\\s+"))
    val wordsPair = wordsRDD.map(word => (word, 1))
    val wordCount = wordsPair.reduceByKey(_ + _)
    println(wordCount.collect.toList)

    // Dataset
    val wordsDs = ds.flatMap(value => value.split("\\s+"))
    val wordsPairDs = wordsDs.groupByKey(value => value)
    val wordCountDs = wordsPairDs.count()
    wordCountDs.show()

    // Caching 
    println(" cache ")

    //RDD
    rdd.cache()

    //Dataset
    ds.cache()

    // Filter
    println(" filter ")

    // RDD
    val filteredRDD = wordsRDD.filter(value => value == "aaa")
    println(filteredRDD.collect().toList)

    // Dataset
    val filteredDS =wordsDs.filter(value => value == "aaa")
    filteredDS.show()

    // Map Partitions
    
    println(" Map Partitions ")

    // RDD
    val mapPartitionsRDD = rdd.mapPartitions(iterator => 
        List(iterator.count(value => true)).iterator
        )
    println(s"The count each partition is ${mapPartitionsRDD.collect().toList}")

    // Dataset
    val mapPartitionsDs = ds.mapPartitions(iterator => 
        List(iterator.count(value => true)).iterator)
    mapPartitionsDs.show()

    // reduceByKey
    println(" reduceByKey ")

    // RDD
    val reduceCountByRDD = wordsPair.reduceByKey(_+_)
    println(s"The reduceByKey is ${reduceCountByRDD.collect().toList}")

    // Dataset
    val reduceCountByDs = wordsPairDs.mapGroups((key, values) => (key,values.length))
    reduceCountByDs.show()

    // Conversions
    println(" Conversions ")

    // RDD
    val dsToRDD = ds.rdd
    println(s"The Conversions ${dsToRDD.collect().toList}")

    // Dataset
    val rddStringToRowRDD = rdd.map(value => Row(value))
    val dfschema = StructType(Array(StructField("value",StringType)))
    val rddToDF = sparkSession.createDataFrame(rddStringToRowRDD,dfschema)
    val rDDToDataSet = rddToDF.as[String]
    rDDToDataSet.show()

    // Double Based Operations

    println(" Double Based OPerations")

    // RDD
    val doubleRDD = sparkContext.makeRDD(List(1.0,5.0,8.9,9.0))
    val rddSum = doubleRDD.sum()
    val rddMean = doubleRDD.mean()

    println(s"sum is $rddSum")
    println(s"mean is $rddMean")

    // Dataset
    val rowRDD = doubleRDD.map(value => Row.fromSeq(List(value)))
    val schema = StructType(Array(StructField("value", DoubleType)))
    val doubleDS = sparkSession.createDataFrame(rowRDD, schema)

    import org.apache.spark.sql.functions._
    doubleDS.agg(sum("value")).show()
    doubleDS.agg(mean("value")).show()

    // Reduce API
    println(" Reduce API ")

    // RDD
    val rddReduce = doubleRDD.reduce((a,b) => a + b)

    println(s"Ths reduce api ${rddReduce}")

    // Dataset
    val dsReduce = doubleDS.reduce((row1, row2) => Row(row1.getDouble(0) + row2.getDouble(0)))
    println(s"Ths reduce api ${dsReduce}")

  }
}
