package org.github.cmonkey.spark
import java.io.PrintWriter

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import scala.io.Source

object PipeinSpark{

  def main(args: Array[String]): Unit = {

    val sc = SparkSession.builder.appName("Pipe in Spark").master("local").getOrCreate().sparkContext

    // Create RDD
    val data = List("hi", "hello", "how", "are", "you")

    val dataRDD = sc.makeRDD(data)

    // Create a shell script
    //
    // #!/bin/sh
    // echo "Running shell script"
    // while read LINE; do
    //     echo ${LINE}
    // done
    //

    // Pipe rdd data to shell script

    val scriptPath = "/home/hadoop/echo.sh"
    val pipeRDD = dataRDD.pipe(scriptPath)
    pipeRDD.collect()

    val command = "ls"

    val proc = Runtime.getRuntime.exec(Array(command))

    new Thread("stderr reader for " + command){
      override def run(){
        for(line <- Source.fromInputStream(proc.getErrorStream).getLines)
          System.err.println(line)
      }
    }.start()

    val lineList = List("hello", "how", "are", "you")

    new Thread("stdin writer for " + command){
      override def run(){
        val out = new PrintWriter(proc.getOutputStream)
        for(elem <- lineList)
          out.println(elem)
        out.close()
      }
    }.start()

    val outputLines = Source.fromInputStream(proc.getInputStream).getLines
    println(outputLines.toList)
  }
}
