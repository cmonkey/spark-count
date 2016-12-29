package com.sparkcount

import org.apache.spark.sql.SparkSession

object ApacheSparkExamplesOfTransformations{

  def main(args: Array[String]): Unit = {

    val sc = SparkSession.builder().appName("Transformations").master("local[*]").getOrCreate().sparkContext

    val flatmap = sc.parallelize((List(1,2,3))).flatMap(x => List(x,x,x))

    println(s"This transformation flatmap = ${flatmap.collect().foreach(println)}")

    val map = sc.parallelize((List(1,2,3))).map( x => List(x,x,x))

    println(s"This transformation map = ${map.collect().foreach(println)}")

    val file = sc.textFile("src/main/resources/people.txt")
    val words = file.filter(line => line.contains("aaa"))
    words.collect().foreach(println)

    val parallel = sc.parallelize(1 to 9, 3)

    val mp = parallel.mapPartitions(x => List(x.next).iterator)
    println(s"This mp collect = ${mp.collect()}")

    val parallel_1 = sc.parallelize(1 to 9)

    val mp1 = parallel_1.mapPartitions(x => List(x.next).iterator)
    println(s"This mp1 collect = ${mp1.collect()}")

    val parallelf = sc.parallelize(1 to 9)

    val pf = parallelf.mapPartitionsWithIndex((index: Int, it: Iterator[Int]) =>
      it.toList.map(x => index + ". " + x).iterator)
    println(s"This pf = ${pf.collect()}")

    val parallelf2 = sc.parallelize(1 to 9, 3)

    val pf2 = parallelf2.mapPartitionsWithIndex((index: Int, it: Iterator[Int]) => it.toList.map(x =>
        index + ". " + x
        ).iterator)

    println(s"This pf2 = ${pf2.collect()}")

    val sample = sc.parallelize(1 to 9)

    println(s"This sample 2 count = ${sample.sample(true, .2).count}")

    println(s"This sample 2 count = ${sample.sample(true, .2).count}")

    println(s"This sample 1 count = ${sample.sample(true, .1).count}")

    val union = sc.parallelize(1 to 9)

    val union2 = sc.parallelize(5 to 15)

    println(s"This union = ${union.union(union2).collect().foreach(println)}")

    val intersection = sc.parallelize(1 to 9)

    val intersection2 = sc.parallelize(5 to 15)

    println(s"This intersection = ${intersection.intersection(intersection2).collect().foreach(println)}")

    val distinct = sc.parallelize(1 to 9)

    val distinct2 = sc.parallelize(5 to 15)

    println(s"This distinct = ${distinct.union(distinct2).distinct.collect().foreach(println)}")
  }
}
