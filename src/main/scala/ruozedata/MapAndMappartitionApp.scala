package ruozedata

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
  * 是Transformation
  */
object MapAndMappartitionApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("MapMappartitionApp")
    val sc = new SparkContext(sparkConf)


    val students = new ListBuffer[String]
    for (i <- 1 to 100) {
      students += "stu:" + i
    }


    val stuRDD = sc.parallelize(students, 10)

    //TODO...需要把student存储到数据库中去
    //    myMap(stuRDD)
    myMappartition(stuRDD)

    sc.stop()
  }

  def myMappartition(rdd: RDD[String]): Unit = {
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


  def myMap(rdd: RDD[String]): Unit = {

    rdd.map(x => {
      val conn = DBUtils.getConnection()
      println(conn + "~~~~~~~~~") //有多少个元素就创建多少个Connection

      //TODO...保存数据到DB
      DBUtils.returnConnection(conn)

      x
    }).foreach(println)

  }

}
