package DataSource

import org.apache.spark.internal.Logging
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.sql.sources.{BaseRelation, TableScan}
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

class TextRelation(override val sqlContext: SQLContext,
                   path: String,
                   userSchema: StructType)
  extends BaseRelation
    with TableScan
    with Logging {


  override def schema: StructType = {
    if (userSchema != null) {
      userSchema
    } else {
      StructType(
        StructField("id", LongType, false) ::
          StructField("name", StringType, false) ::
          StructField("gender", StringType, false) ::
          StructField("salary", LongType, false) ::
          StructField("comm", LongType, false) :: Nil
      )
    }
  }

  override def buildScan(): RDD[Row] = {
    logWarning("this is pk custom buildScan....")

    //path ==> RDD[Row]
    val rdd = sqlContext.sparkContext.wholeTextFiles(path).map(x => x._2)
    val schemaFields = schema.fields  //拿到所有自定义的schema

    /**
      * TODO... 把fileld作用到rdd上去
      *
      * 如何根据schema的fields的数据类型以及字段顺序整合到rdd中
      */

    val rows = rdd.map(filterContent => {
      val lines = filterContent.split("\n")
      val data = lines.map(x => x.split(",").map(x => x.trim).toSeq)

      //0:id ...
      val typedValues = data.map(x => x.zipWithIndex.map {
        case (value, index) => {
          val colName = schemaFields(index).name   //拿到每个列名，id/name

          Utils.caseTo(if (colName.equalsIgnoreCase("gender")) {
            if (value == "0") {
              "男"
            } else if (value == "1") {
              "女"
            } else {
              "未知"
            }
          } else {
            value
          }, schemaFields(index).dataType
          )
        }
      })

      /**
        * TODO... 解析Schema
        */
      typedValues.map(x => Row.fromSeq(x))
    })
    rows.flatMap(x => x)
  }


}
