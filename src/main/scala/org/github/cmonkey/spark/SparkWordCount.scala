package org.github.cmonkey.spark
import org.apache.spark.SparkContext 

object SparkWordCount{

  def main(args: Array[String]): Unit = {

    val env = new SparkContext("local", "SparkWordCount")

    val data = List("hi", "how are you", "hi")

    val dataSet = env.parallelize(data)

    val words = dataSet.flatMap(value => value.split("\\s+"))

    val mappedWords = words.map(value => (value, 1))

    val sum = mappedWords.reduceByKey(_+_)

    println(sum.collect())
  }
}
