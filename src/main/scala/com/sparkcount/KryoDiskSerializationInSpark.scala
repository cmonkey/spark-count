package com.sparkcount

import java.io.ByteArrayOutputStream

import com.esotericsoftware.kryo.io.Input
import org.apache.hadoop.io.{BytesWritable, NullWritable}
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.serializer.KryoSerializer

import scala.reflect.ClassTag

class Person(val name: String)

object KryoDiskSerializationInSpark{

  def main(args: Array[String]): Unit = {
    if(args.length < 1){
      println("Please provide output path")
      return 
    }

    val outputPath = args(0)

    val conf = new SparkConf().setMaster("local").setAppName("kryoexample")
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

    val sc = new SparkContext(conf)

    val personList = 1 to 10000 map (value => new Person(value + ""))
    val personRDD = sc.makeRDD(personList)

    saveAsObjectFile(personRDD, outputPath)

    val rdd = objectFile[Person](sc, outputPath)

    println(rdd.map(person => person.name).collect().toList)
  }

  def saveAsObjectFile[T: ClassTag](rdd: RDD[T], path: String): Unit = {
    val kryoSerializer = new KryoSerializer(rdd.context.getConf)

    rdd.mapPartitions(iter => iter.grouped(10)
      .map(_.toArray))
      .map(splitArray => {
        val kryo = kryoSerializer.newKryo()

        val bao = new ByteArrayOutputStream()
        val output = kryoSerializer.newKryoOutput()
        output.setOutputStream(bao)
        kryo.writeClassAndObject(output, splitArray)
        output.close()

        val byteWritable = new BytesWritable(bao.toByteArray)
        (NullWritable.get(), byteWritable)
      }).saveAsSequenceFile(path)

  }

  def objectFile[T](sc: SparkContext, path: String, minPartitions: Int = 1)(implicit ct: ClassTag[T]) = {
    val kryoSerializer = new KryoSerializer(sc.getConf)
    sc.sequenceFile(path, classOf[NullWritable], classOf[BytesWritable], 
      minPartitions)
        .flatMap(x => {
          val kryo = kryoSerializer.newKryo()
          val input = new Input()
          input.setBuffer(x._2.getBytes)
          val data = kryo.readClassAndObject(input)
          val dataObject = data.asInstanceOf[Array[T]]
          dataObject
        })
  }

}
