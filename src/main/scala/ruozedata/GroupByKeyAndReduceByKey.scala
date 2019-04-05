package ruozedata

import org.apache.spark.{SparkConf, SparkContext}

object GroupByKeyAndReduceByKey {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("GroupByKeyAndReduceByKey")
    val sc = new SparkContext(sparkConf)

    //TODO...实现WC统计的功能

    sc.textFile("file:///")
      .flatMap(_.split("\t"))
      .map((_, 1))
      .reduceByKey(_ + _)
      .collect()
      .foreach(println)


    sc.textFile("file:///")
      .flatMap(_.split("\t"))
      .map((_, 1))
      .groupByKey()
      .map(x => (x._1, x._2.sum))
      .collect()


    sc.stop()
  }
}
