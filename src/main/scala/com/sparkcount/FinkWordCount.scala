package com.sparkcount

//import org.apache.flink.api.scala.ExecutionEnvironment

object FinkWordCount{

  def main(args: Array[String]): Unit = {
    /*
    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = List("hi", "how are you", "hi")

    val dataSet = env.fromCollection(data)

    val words = dataSet.flatMap(value => value.split("\\s+"))

    val mappedWords = words.map(value => (value, 1))

    val grouped = mappedWords.groupBy(0)

    val sum = grouped.sum(1)

    println(sum.collect())
    */
  }
}
