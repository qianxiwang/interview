package ruozedata

import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object SerializationApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("SerializationApp")
    val sc = new SparkContext(sparkConf)

    val flag = sc.getConf.getInt("spark.flag", 0)
    //不建议写在代码里，建议写在spark-submit里使用--conf传入进去
    sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    sparkConf.registerKryoClasses(Array(classOf[Info]))

    val infos = new ArrayBuffer[Info]()
    val names = Array[String]("PK", "jspon", "huhu")
    val genders = Array[String]("male", "female")
    val addresses = Array[String]("beijing", "shanghai", "chengdu", "hangzhou")


    for (i <- 1 to 1000000) {
      val name = names(Random.nextInt(3))
      val age = Random.nextInt(100)
      val gender = genders(Random.nextInt(2))
      val address = addresses(Random.nextInt(4))

      infos += Info(name, age, gender, address)
    }

    val rdd = sc.parallelize(infos)

    if (flag == 0) {
      rdd.persist(StorageLevel.MEMORY_ONLY)
    } else {
      rdd.persist(StorageLevel.MEMORY_ONLY_SER)
    }

    println(rdd.count())

    Thread.sleep(1000 * 60)

    sc.stop()
  }

  case class Info(name: String, age: Int, gender: String, address: String)

}
