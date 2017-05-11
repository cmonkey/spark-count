package com.sparkcount;

import java.io.File

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession;

object ReadFiles{

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("sync photos").master("local[*]").getOrCreate();

    val sc = spark.sparkContext

    val fileBasePath = "/home/cmonkey/software/photos/"
    val hadoopBaseUrl = "hdfs://10.0.0.15:9000"

    saveFileToHdfs(fileBasePath+"camera/", hadoopBaseUrl, "/photos/camera/", sc)
    saveFileToHdfs(fileBasePath+"/贝贝/100days/", hadoopBaseUrl, "/photos/baby/100days/", sc)
    saveFileToHdfs(fileBasePath+"/贝贝/100days/resize/", hadoopBaseUrl, "/photos/baby/100days/resize/", sc)
    saveFileToHdfs(fileBasePath+"/贝贝/1year/", hadoopBaseUrl, "/photos/baby/1year/", sc)

    spark.close()
  }

  def saveFileToHdfs(filePath: String, hadoopBaseUrl: String, hadoopOutput: String, sc: SparkContext): Unit = {
    val file = new File(filePath)

    val hadoopConf = new org.apache.hadoop.conf.Configuration()
    val hdfs = org.apache.hadoop.fs.FileSystem.get(new java.net.URI(hadoopBaseUrl), hadoopConf)

    file.listFiles().foreach(f =>
      try{
        val hadoopOutputUrl = hadoopBaseUrl + hadoopOutput + f.getName
        println(hadoopOutputUrl)
        hdfs.delete(new org.apache.hadoop.fs.Path(hadoopOutputUrl), true)
        sc.textFile(f.getPath).saveAsTextFile(hadoopOutputUrl)
      }catch{

        case _: Throwable => {}
      }
    )
  }
}

