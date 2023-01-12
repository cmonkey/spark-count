package org.github.cmonkey.spark

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.linalg.Vector
import org.apache.spark.sql.{Row, SparkSession}

object PipelineApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("PipelineApp").getOrCreate()

    val training = spark.createDataFrame(Seq(
      (0L, "a b c d e spark", 1.0),
      (1L, "b d", 0.0),
      (2L, "spark f g h", 1.0),
      (3L, "hadoop mapreduce", 0.0)
    )).toDF("id", "text", "label")

    val tokenizer = new Tokenizer()
      .setInputCol("text")
      .setOutputCol("words")

    val hashingTf = new HashingTF()
      .setNumFeatures(1000)
      .setInputCol(tokenizer.getOutputCol)
      .setOutputCol("features")

    val lr = new LogisticRegression()
      .setMaxIter(10)
      .setRegParam(0.0001)

    val pipeline = new Pipeline()
      .setStages(Array(tokenizer, hashingTf, lr))

    val model = pipeline.fit(training)

    model.write.overwrite().save("/tmp/spark-logistic-regression-model")
    pipeline.write.overwrite().save("/tmp/unfit-lr-model")

    val sameModel = PipelineModel.load("/tmp/spark-logistic-regression-model")

    val test = spark.createDataFrame(Seq(
      (4L, "spark i j k"),
      (5L, "l m n"),
      (6L, "spark hadoop spark"),
      (7L, "apache hadoop")
    )).toDF("id", "text")

    val modelCollect = model.transform(test)
      .select("id", "text", "probability", "prediction")
      .collect()

    modelCollect.foreach{
      case Row(id:Long, text:String, prob:Vector, prediction:Double) => {
        println(s"$id, $text --> prob = $prob, prediction=$prediction")
      }
    }
  }

}
