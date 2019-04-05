package ruozedata

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * foreach系列常用于将数据写入到数据库中去(是Action)
  */
object ForeachAndForeachpartitionApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("ForeachAndForeachpartitionApp")
    val sc = new SparkContext(sparkConf)

    val students = new ListBuffer[String]
    for (i <- 1 to 100) {
      students += "stu:" + i
    }

    val stuRDD = sc.parallelize(students)
    //    myForeach(stuRDD)
    myForeachpartition(stuRDD)


    sc.stop()
  }

  def myForeachpartition(rdd: RDD[String]): Unit = {
    println(rdd.partitions.size) //查看分区数量

    rdd.mapPartitions(partition => {

      //每个partition拿一个connection
      val conn = DBUtils.getConnection()
      println(conn + "~~~~~~~~~")

      //TODO...保存数据到DB
      DBUtils.returnConnection(conn)

      partition
    }).foreach(println)
  }

  def myForeach(rdd: RDD[String]) {
    rdd.foreach(x => {
      val conn = DBUtils.getConnection()

      DBUtils.returnConnection(conn)
    })

  }
}
