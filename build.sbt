name := "sparktraining"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.5.2" % "provided",
  "org.apache.spark" %% "spark-streaming" % "1.5.2" % "provided",
  "org.apache.spark" %% "spark-streaming-kafka" % "1.5.2",
  "org.apache.kafka" %% "kafka" % "0.8.2.2"
)

resolvers ++= Seq(
    "Akka Repository" at "http://repo.akka.io/releases/",
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Twitter Maven Repo" at "http://maven.twttr.com/"
)
