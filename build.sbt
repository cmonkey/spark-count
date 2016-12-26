name := "spark-count"
version := "0.0.1"

scalaVersion := "2.11.8"

javaOptions ++= Seq("-source", "1.8", "-target", "1.8")


libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core_2.11" % "2.0.1",
    "org.apache.spark" % "spark-sql_2.11" % "2.0.1",
    "com.typesafe.akka" % "akka-actor_2.10" % "2.2.5",
  "com.typesafe.akka" % "akka-remote_2.10" % "2.2.5"
)
