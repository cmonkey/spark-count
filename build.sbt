name := "spark-count"
version := "0.0.1"

scalaVersion := "2.12.2"

javaOptions ++= Seq("-source", "1.8", "-target", "1.8")


libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.0.1",
    "org.apache.spark" % "spark-sql_2.11" % "2.0.1",
    "org.apache.spark" % "spark-graphx_2.11" % "2.0.1",
    "org.apache.spark" % "spark-hive_2.11" % "2.0.1",
    "org.apache.spark" % "spark-streaming_2.11" % "2.0.1",
    "org.apache.flink" %% "flink-scala" % "1.0.0",
    "com.typesafe.akka" % "akka-actor_2.11" % "2.3.7",
    "com.typesafe.akka" % "akka-remote_2.11" % "2.3.7",
    "org.jblas" % "jblas" % "1.2.4",
    "junit" % "junit" % "4.12",
    "org.seleniumhq.selenium" % "selenium-java" % "3.0.1",
    "mysql" % "mysql-connector-java" % "5.1.38"
)
