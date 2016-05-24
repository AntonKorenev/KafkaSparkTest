import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object KafkaStreaming{
  def main(args: Array[String]) {
    val brokers = "localhost:9092"
    val zookeeper = "localhost:2181"
    val sessionTimeout =  "1000"
    val syncTimeout = "1000"
    val commitInterval = "1000"
    val group = "test-consumer-group"
    val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers, "zookeeper.connect" -> zookeeper, "zookeeper.session.timeout.ms" -> sessionTimeout, "zookeeper.sync.time.ms" -> syncTimeout, "auto.commit.interval.ms" -> commitInterval, "group.id" -> group)
    val topicsSet = Set("ClusterTestTopic")

    // Create context with 2 second batch interval
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("DirectKafkaWordCount").set("spark.eventLog.enabled","false")
    val ssc = new StreamingContext(sparkConf, Seconds(20))

    // Create direct kafka stream with brokers and topics

    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicsSet)

    // Get the lines, split them into words, count the words and print
    val wordCounts = messages.map(_._2).flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
    wordCounts.foreachRDD(rdd => rdd.saveAsTextFile("result"))

    ssc.start()
    ssc.awaitTermination()
  }
}
