import org.apache.spark.{SparkConf, SparkContext}

object Example{
  var instance: Example = new Example("default_name")

  def getInstance(): Example = {
    return instance
  }

  def init(name: String){
    instance = new Example(name)
  }
}

class Example private(nameP: String) extends Serializable{
  var name = nameP
}

object SparkLocalDebug{
  def main(args: Array[String]) = {
    Example.init("To create happiness wiht money")

    val sc = new SparkContext(new SparkConf().setAppName("test"))

    val rdd = sc.parallelize(1 to 10, 3)

    rdd.map( x => (
        x + "_" + Example.getInstance().name
      )).collect.foreach(println)
  }

}

