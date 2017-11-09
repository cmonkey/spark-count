package org.github.cmonkey.spark
import org.apache.spark.sql.SparkSession

/**
  * http://centaur.github.io/repox/
  */
object Repox {

  def main(args: Array[String]): Unit = {

    val ss = SparkSession.builder().master("local").appName("Repox").getOrCreate()

    val sc = ss.sparkContext

    val filename = args(0)

    print(s"input filename = ${filename}")

    val data = sc.textFile(filename)

    val opsData = data.map(d => (d.substring(0, 1), d.substring(1, d.length).toFloat))

    val addOps = opsData.filter(f => f._1 == "+")

    val subOps = opsData.filter(f => f._1 == "-")

    val addAmount = addOps.reduceByKey(_ + _).map(f => f._2).collect().last

    val subAmount = subOps.reduceByKey(_ + _).map(f => f._2).collect().last

    print(s"捐款总额${addAmount}, 支出总额${subAmount}, 结余${addAmount - subAmount}")
  }

}
