import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

import scala.util.MurmurHash
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.hashing.MurmurHash3

object main{

  def main(args: Array[String]){

    val sc = new SparkContext(new SparkConf().setAppName("graphx"))

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val connInfoMap = Map("url" -> "jdbc:mysql://10.204.43.88:3306/demo", 
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "us2004_companies", 
      "user" -> "root", 
      "password" -> "root")

    val compDf = sqlContext.read.format("jdbc").options(connInfoMap).load()
    compDf.createOrReplaceTempView("companies")

    val ids =  sqlContext.sql("select * from companies")
    ids.printSchema()
    //ids.first()
    //ids.select("url").show(5)
    //println(ids.count())

    //ids.collect().foreach(println)

    val comps = compDf.select(compDf("id"), 
      compDf("companyname"),
      compDf("bod")
      )

    comps.show(100)

    /**
    val compRDD1 = comps.map(t =>
        (
          t.getAs[Int]("id"),
          t.getAs[String]("companyname"),
          t.getAs[String]("bod").split(",")

          )
        )
    val compMapDF = compRDD1.flatMap(comp => {

      comp._3.map(toCompany(comp._1, comp._2,_))
    }).toDF
    val options = Map (
      "url" ->"jdbc:mysql://10.204.43.88:3306/demo",
      "driver"->"com.mysql.jdbc.Driver",
      "dbtable"->"us2004_directors" ,
      "user" ->"root" ,
      "password"->"root")

    val dirDF = sqlContext.read.format("jdbc").options(options).load()
    val dirnames = dirDF.select(
      dirDF("id"),
      dirDF("firstname"),
      dirDF("lastname"),
      dirDF("boards"))

    val directorsRDD = dirnames.map(t=>(t.getAs[Int]("id"),
    t.getAs[String]("lastname")+" "+t.getAs[String]("firstname"),
    t.getAs[String]("boards").split(",") )  )
    .filter(_._3.length>1)
    val dirMapDF=directorsRDD.
    flatMap(dir=>{dir._3.map(toDirector(dir._1,dir._2,_))})
    .toDF
    */
  }

  def toCompany(id: Int, name: String, bod: String): Company = {
    Company(id.toInt, name, bod.toInt)
  }

  def toDirector(id: Int, name: String, bod: String): Director = {
    Director(id.toInt, name, bod.trim.toInt)
  }

  def toEdge(name: String, company: String): Edge[Int] = {
    Edge(MurmurHash3.stringHash(company),
      MurmurHash3.stringHash(name),
      MurmurHash3.stringHash(name)
      )
  }

}

case class Company(companyid: Int, companyName: String, boardMember: Int) 
case class Director (id: Int, Name: String, memberOf: Int)

