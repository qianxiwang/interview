package DataSource

import org.apache.spark.sql.SparkSession

/**
  * 自定义实现外部数据源
  */
object CustomApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("")
      .master("local[2]")
      .getOrCreate()

    val people = spark.read.format("DataSource.DefaultSource").option("path", "/wangqingguo/bigdata/testdata/extds.data").load()

    people.show()


    spark.stop()
  }
}
