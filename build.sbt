

name := "sparktraining"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.5.2",
  "org.apache.spark" %% "spark-streaming" % "1.5.2",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.5.2",
  "org.apache.kafka" %% "kafka" % "0.8.2.2"
)

resolvers ++= Seq(
    "Akka Repository" at "http://repo.akka.io/releases/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Twitter Maven Repo" at "http://maven.twttr.com/"
)

assemblyJarName in assembly := "kafka-streaming.jar"
assemblyMergeStrategy in assembly := {
  case path@PathList("META-INF", xs @ _*) if path.toString.contains("META-INF/services/org.apache.hadoop.fs.FileSystem") =>
    MergeStrategy.first
  case path@PathList("META-INF", xs @ _*) if path.toString.contains("META-INF/services/java.sql.Driver") =>
    MergeStrategy.first
  case path@PathList("META-INF", xs @ _*) =>
    MergeStrategy.discard
  case x => MergeStrategy.first
}