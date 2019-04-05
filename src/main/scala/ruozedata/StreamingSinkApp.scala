package ruozedata

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingSinkApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StreamingSinkApp")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val lines = ssc.socketTextStream("127.0.0.1", 4444)
    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    //TODO...需要将socket端接收到的数据进行处理之后写入MySQL

    result.foreachRDD(rdd => {
      val conn = createCnnnection()

      rdd.foreach(pair => {
        val sql = s"insert into wc(word,count) values('${pair._1}','${pair._2}')"

        conn.createStatement().execute(sql)
        conn.close()
      })

    })

    ssc.start()
    ssc.awaitTermination()
  }

  /**
    * 创建MySQL的Connection
    */
  def createCnnnection() = {
    Class.forName("com.mysql.jdbc.Driver")

    DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root", "000000")

  }
}
